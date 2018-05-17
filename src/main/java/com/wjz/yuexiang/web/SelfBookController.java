package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.BookReviewService;
import com.wjz.yuexiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
        if(user.getBookForests() == null || user.getBookForests().isEmpty()){
            attributes.addFlashAttribute("lostMessage","你还没有加入任何书林，赶紧去加入吧！");
            return "redirect:/user/book_review_list";
        }
        //bookReviewService.getBookReview(id,3,user);  //确定该书评是当前登录用户的，并且是通过审核的(不合法会在下层报错)
        model.addAttribute("bookReviewId",id);
        model.addAttribute("bookForests",user.getBookForests());
        return "book_edit";
    }
}
