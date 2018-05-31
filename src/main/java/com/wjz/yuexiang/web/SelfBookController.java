package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.Book;
import com.wjz.yuexiang.po.BookForest;
import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.BookForestService;
import com.wjz.yuexiang.service.BookReviewService;
import com.wjz.yuexiang.service.BookService;
import com.wjz.yuexiang.service.UserService;
import com.wjz.yuexiang.vo.BookPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jinzi Wu at 11:20 on 2018/5/17.
 */
@Controller
@RequestMapping("/user")
public class SelfBookController {

    @Autowired
    private BookReviewService bookReviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookForestService bookForestService;

    @Autowired
    private BookService bookService;

    /**
     * 书籍编辑页面
     */
    @GetMapping("/{id}/book_edit")
    public String bookEditPage(@PathVariable Long id,
                                HttpSession session,
                                RedirectAttributes attributes,
                                Model model){
        User user = (User) session.getAttribute("user");
        userService.getUser(user.getId());
        List<BookForest> bookForests = bookForestService.getBookForestsByUser(user.getId());
        if(bookForests == null || bookForests.isEmpty()){
            attributes.addFlashAttribute("lostMessage","你还没有加入任何书林，赶紧去加入吧！");
            return "redirect:/user/book_review_list";
        }
        model.addAttribute("bookReviewId",id);
        model.addAttribute("bookForests",bookForests);
        BookPost bookPost = new BookPost();
        model.addAttribute("bookPost",bookPost);
        return "book_edit";
    }

    @PostMapping("/book")
    public String BookPost(@Valid BookPost bookPost,
                           BindingResult result,
                           HttpSession session,
                           RedirectAttributes attributes){

        User user = (User) session.getAttribute("user");
        if(result.hasErrors()){
            return "book_edit";
        }

        Book book = new Book();

        book.setBookReviewId(bookPost.getBookReviewId());
        book.setRemark(bookPost.getRemark());
        book.setShareWay(bookPost.getShareWay());
        book.setContactInfo(bookPost.getContactInfo());

        BookReview bookReview = bookReviewService.getBookReview(bookPost.getBookReviewId(),3,user);  //确定该书评是当前登录用户的，并且是通过审核的

        bookService.saveBook(book,user,bookReview,bookPost.getBookForestId());

        attributes.addFlashAttribute("pMessage","书籍创建成功");
        return "redirect:/user/book_list";
    }

    @GetMapping("/book_list")
    public String BookListPage(@PageableDefault(size = 15,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable,
                               HttpSession session,
                               Model model){
        User user = (User) session.getAttribute("user");
        Page<Book> page = bookService.books(user,pageable);
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            model.addAttribute("nMessage","还没有书籍！");
            return "book_list";
        }
        model.addAttribute("page",page);
        return "book_list";
    }
}
