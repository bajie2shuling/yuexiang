package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.BookForestCreateApply;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.BookForestCreateApplyService;
import com.wjz.yuexiang.vo.BookForestCreateApplyPost;
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

/**
 * Created by Jinzi Wu at 18:01 on 2018/5/17.
 */
@Controller
@RequestMapping("/user")
public class BookForestCreateApplyController {

    @Autowired
    private BookForestCreateApplyService bookForestCreateApplyService;

    @GetMapping("/bookforest/create/apply")
    public String bookForestCreateApplyPage(Model model){
        BookForestCreateApplyPost bookForestCreateApplyPost = new BookForestCreateApplyPost();
        model.addAttribute("bookForestCreateApplyPost",bookForestCreateApplyPost);
        return "book_forest_create_apply";
    }

    @PostMapping("/bookforest/create/apply")
    public String BookForestCreateApply(@Valid BookForestCreateApplyPost bookForestCreateApplyPost,
                                        BindingResult result,
                                        HttpSession session,
                                        RedirectAttributes attributes){
        User user = (User) session.getAttribute("user");
        if(result.hasErrors()){
            return "book_forest_create_apply";
        }

        BookForestCreateApply bookForestCreateApply = bookForestCreateApplyPost.convertToBookForestCreateApply();
        if(bookForestCreateApply.getId() == null){
            bookForestCreateApply.setUser(user);
            bookForestCreateApplyService.saveBookForestCreateApply(bookForestCreateApply);
        }else{
            bookForestCreateApplyService.updateBookForestCreateApply(bookForestCreateApply.getId(),user,bookForestCreateApply);
        }
        attributes.addFlashAttribute("pMessage","书林创建申请操作成功");
        return "redirect:/user/book_forest_create_apply_list";
    }

    @GetMapping("/book_forest_create_apply_list")
    public String bookForestCreateApplyList(@PageableDefault(size = 15,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable,
                                            HttpSession session,
                                            Model model) {
        User user = (User) session.getAttribute("user");
        Page<BookForestCreateApply> page = bookForestCreateApplyService.bookForestCreateApplies(user,pageable);
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            model.addAttribute("nMessage","还没有书林创建申请,赶紧去申请吧！");
            return "book_forest_create_apply_list";
        }
        model.addAttribute("page",page);
        return "book_forest_create_apply_list";
    }

    @GetMapping("/book_forest_create_apply/{id}/change")
    public String bookForestCreateApplyChangePage(@PathVariable Long id,
                                                  HttpSession session,
                                                  Model model){
        User user = (User) session.getAttribute("user");
        BookForestCreateApply bookForestCreateApply = bookForestCreateApplyService.getBookForestCreateApply(id,user);
        model.addAttribute("bookForestCreateApplyPost",bookForestCreateApply);
        return "book_forest_create_apply";
    }

    @GetMapping("/book_forest_create_apply/{id}/delete")
    public String bookForestCreateApplyDelete(@PathVariable Long id,
                                              HttpSession session,
                                              RedirectAttributes attributes){
        User user = (User) session.getAttribute("user");
        bookForestCreateApplyService.deleteBookForestCreateApply(id,user);
        attributes.addFlashAttribute("pMessage","删除书林创建申请成功！");
        return "redirect:/user/book_forest_create_apply_list";
    }
}
