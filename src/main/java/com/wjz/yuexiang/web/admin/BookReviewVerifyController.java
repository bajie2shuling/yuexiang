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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Created by Jinzi Wu at 21:25 on 2018/5/5.
 */
@Controller
@RequestMapping("/admin")
public class BookReviewVerifyController {

    @Autowired
    private BookReviewService bookReviewService;

    /**
     * 书评审核列表页面
     * @param pageable
     * @param attributes
     * @param model
     * @return
     */
    @GetMapping("/book_review_verify_list")
    public String bookReviewVerifyListPage(@PageableDefault(size = 8,sort = {"updateTime","createTime"},direction = Sort.Direction.ASC) Pageable pageable,
                                       RedirectAttributes attributes,
                                       Model model){
        Page<BookReview> page = bookReviewService.bookReviews(1,pageable);   //只取待审核状态的书评
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            model.addAttribute("verifyMessage","还没有待审核的书评");
            return "admin/book_review_verify_list";
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            attributes.addFlashAttribute("lostMessage","很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //防止用户恶意在地址栏输入页码
            return "redirect:/admin/book_review_verify_list";
        }
        model.addAttribute("page",page);
        return "admin/book_review_verify_list";
    }

    /**
     * 书评审核页面
     */
    @GetMapping("/book_review/{id}/verify")
    public String bookReviewVerifyPage(@PathVariable Long id,
                                       RedirectAttributes attributes,
                                       Model model){
        BookReview bookReview = bookReviewService.getBookReviewAndConvert(id,1);      //只能查看待审核状态的书评
        if(bookReview == null){
            attributes.addFlashAttribute("lostMessage","该篇书评不存在");       //防止用户恶意在地址栏输入书评id
            return "redirect:/admin/book_review_verify_list";
        }
        model.addAttribute("bookReview",bookReview);
        return "admin/book_review_verify";
    }
}
