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
                                       RedirectAttributes attributes,
                                       Model model){
        BookReview bookReview = bookReviewService.getBookReviewSetViewsAndConvert(id,3);      //只能查看审核通过的书评,并更新浏览次数
        if(bookReview == null){
            attributes.addFlashAttribute("nMessage","该篇书评不存在");       //防止用户恶意在地址栏输入书评id
            return "redirect:/user/index";
        }
        model.addAttribute("bookReview",bookReview);
        return "book_review";
    }

    @GetMapping("/{id}/homepage")
    public String homePage(@PageableDefault(size = 5,sort = {"publishTime"},direction = Sort.Direction.DESC) Pageable pageable,
                           @PathVariable Long id,
                           RedirectAttributes attributes,
                           Model model){
        User user = userService.getUser(id);
        user.setPassword(null);

        if(user == null){
            attributes.addFlashAttribute("nMessage","抱歉，该用户不存在");
            return "redirect:/user/index";
        }

        Page<BookReview> page = bookReviewService.bookReviews(user,3,pageable);
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            model.addAttribute("nMessage","该用户还没有书评");
            return "home_page";
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            attributes.addFlashAttribute("nMessage","很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //spring从0页开始
            return "redirect:/user/homepage/"+id;
        }
        model.addAttribute("page",page);
        model.addAttribute("user",user);
        return "home_page";
    }
}
