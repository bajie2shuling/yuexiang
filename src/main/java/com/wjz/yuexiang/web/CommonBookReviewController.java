package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
