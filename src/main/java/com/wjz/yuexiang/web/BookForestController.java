package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.Book;
import com.wjz.yuexiang.po.BookForest;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.BookForestService;
import com.wjz.yuexiang.service.BookService;
import com.wjz.yuexiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Jinzi Wu at 22:55 on 2018/5/17.
 */
@Controller
@RequestMapping("/user")
public class BookForestController {


    @Autowired
    private UserService userService;

    @Autowired
    private BookForestService bookForestService;

    @Autowired
    private BookService bookService;

    @GetMapping("/book_forests")
    public String bookForestsPage(HttpSession session,
                                  Model model) {
        User user = (User) session.getAttribute("user");
        userService.getUser(user.getId());
        List<BookForest> bookForests = bookForestService.getBookForestsByUser(user.getId());
        if (bookForests == null || bookForests.isEmpty()) {
            model.addAttribute("nMessage", "你还没有加入任何书林，赶紧去加入吧！");
            model.addAttribute("bookForests", bookForests);
            return "book_forests";
        }
        model.addAttribute("bookForests", bookForests);
        return "book_forests";
    }

    @GetMapping("/book_forest/search")
    public String bookForestSearchPage(Model model){
        Page<BookForest> page = new Page<BookForest>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super BookForest, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<BookForest> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<BookForest> iterator() {
                return null;
            }
        };
        model.addAttribute("page",page);
        return "book_forest_search";
    }

    @PostMapping("/book_forest/search")
    public String bookForestSearch(@PageableDefault(size = 9, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        Page<BookForest> page = bookForestService.bookForests("%"+query+"%", pageable);
        if(page.getTotalPages() == 0 ){
            model.addAttribute("page", page);
            model.addAttribute("nMessage", "抱歉，没有结果");
            return "book_forest_search :: bookForests";
        }
        model.addAttribute("page", page);
        return "book_forest_search :: bookForests";
    }

    @GetMapping("/{id}/bookForest")
    public String bookForestHomePage(@PageableDefault(size = 5,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable,
                           @PathVariable Long id,
                           Model model){

        BookForest bookForest = bookForestService.getBookForest(id);
        Page<Book> page = bookService.books(id,pageable);
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            model.addAttribute("bookForest",bookForest);
            model.addAttribute("nMessage","该书林还没有书籍");
            return "book_forest_home_page";
        }
        model.addAttribute("page",page);
        model.addAttribute("bookForest",bookForest);
        return "book_forest_home_page";
    }

    /**
     * 加入或退出书林
     */
    @PostMapping("/join_switch")
    public String joinSwitch(@RequestParam Long id,
                                  HttpSession session,
                                  Model model){
        User user = (User) session.getAttribute("user");
        List<Long> bookForestIds =(List<Long>) session.getAttribute("bookForestIds");
        if(!bookForestService.saveOrDelete(id,user.getId())){  //返回true表示保存，false表示删除
            bookForestIds.remove(id);
            session.setAttribute("bookForestIds",bookForestIds);
        }else {
            bookForestIds.add(id);
            session.setAttribute("bookForestIds",bookForestIds);
        }
        BookForest bookForestBuf = bookForestService.getBookForest(id);
        model.addAttribute("bookForest",bookForestBuf);
        return "book_forest_home_page :: join";
    }
}
