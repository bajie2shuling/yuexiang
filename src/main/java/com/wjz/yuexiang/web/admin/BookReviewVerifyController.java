package com.wjz.yuexiang.web.admin;

import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Jinzi Wu at 21:25 on 2018/5/5.
 */
@Controller
@RequestMapping("/admin")
public class BookReviewVerifyController {

    @Autowired
    private BookReviewService bookReviewService;

    @GetMapping("/book_review_verify_list")
    public String bookReviewVerifyPage(@PageableDefault(size = 8,sort = {"updateTime","createTime"},direction = Sort.Direction.ASC) Pageable pageable,
                                       RedirectAttributes attributes,
                                       Model model){
        Page<BookReview> page = bookReviewService.bookReviews(2,pageable);
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            attributes.addFlashAttribute("lostMessage","很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");
            return "redirect:/admin/book_review_verify_list";
        }
        model.addAttribute("page",page);
        return "book_review_verify_list";
    }
}
