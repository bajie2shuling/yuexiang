package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.BookReviewService;
import com.wjz.yuexiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Jinzi Wu at 0:36 on 2018/5/9.
 */
@Controller
@RequestMapping("/user")
public class CommonBookReviewController {

    @Autowired
    private BookReviewService bookReviewService;

    @Autowired
    private UserService userService;

    /**
     * 审核通过的书评详情页面
     */
    @GetMapping("/book_review/{id}/detail")
    public String bookReviewDetailPage(@PathVariable Long id,
                                       Model model){
        BookReview bookReview = bookReviewService.getBookReviewSetViewsAndConvert(id,3);      //只能查看审核通过的书评,并更新浏览次数
        model.addAttribute("bookReview",bookReview);
        return "book_review";
    }

    @GetMapping("/homepage/{id}/bookreview")
    public String homePage(@PageableDefault(size = 5,sort = {"publishTime"},direction = Sort.Direction.DESC) Pageable pageable,
                           @PathVariable Long id,
                           Model model){
        User user = userService.getUser(id);

        Page<BookReview> page = bookReviewService.bookReviews(user,3,pageable);
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            user.setPassword(null);
            model.addAttribute("user",user);
            model.addAttribute("nMessage","该用户还没有书评");
            return "home_page_book_review";
        }
        user.setPassword(null);
        model.addAttribute("page",page);
        model.addAttribute("user",user);
        return "home_page_book_review";
    }
}
