package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Jinzi Wu at 17:20 on 2018/5/3.
 */
@Controller
@RequestMapping("/user")
public class BookReviewController {

    @Autowired
    private BookReviewService bookReviewService;

    /**
     * 书评编辑页面
     */
    @GetMapping("/book_review_edit")
    public String bookReviewEditPage(Model model){
        BookReview bookReview = new BookReview();
        model.addAttribute("bookReview",bookReview);
        return "book_review_edit";
    }

    /**
     * 书评提交
     */
    @PostMapping("/book_review_edit")
    public String bookReviewPost(BookReview bookReview,
                                 RedirectAttributes attributes,
                                 HttpSession session){
        bookReview.setUser((User)session.getAttribute("user"));
        if(bookReview.getId() == null){
            bookReview.setCreateTime(new Date());   //创建时间
            bookReviewService.saveBookReview(bookReview);
        }else{
            bookReview.setUpdateTime(new Date());   //修改时间
            bookReviewService.updateBookReview(bookReview.getId(),bookReview);
        }
        attributes.addFlashAttribute("message","书评操作成功");
        return "redirect:/book_review";
    }

    /**
     * 书评详情页面
     */
    @GetMapping("/book_review/{id}/detail")
    public String bookReviewPage(@PathVariable Long id,Model model){
        BookReview bookReview = bookReviewService.findBookReviewById(id);
        model.addAttribute("bookReview",bookReview);
        return "book_review";
    }
}
