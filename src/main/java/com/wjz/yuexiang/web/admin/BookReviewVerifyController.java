package com.wjz.yuexiang.web.admin;

import com.wjz.yuexiang.po.Admin;
import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.BookReviewVerifyRecord;
import com.wjz.yuexiang.service.BookReviewService;
import com.wjz.yuexiang.service.BookReviewVerifyRecordService;
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

import javax.servlet.http.HttpSession;


/**
 * Created by Jinzi Wu at 21:25 on 2018/5/5.
 */
@Controller
@RequestMapping("/admin")
public class BookReviewVerifyController {

    @Autowired
    private BookReviewService bookReviewService;

    @Autowired
    private BookReviewVerifyRecordService bookReviewVerifyRecordService;

    /**
     * 书评审核列表页面
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/book_review_verify_list")
    public String bookReviewVerifyListPage(@PageableDefault(size = 15,sort = {"publishTime"},direction = Sort.Direction.ASC) Pageable pageable,
                                       Model model){
        Page<BookReview> page = bookReviewService.bookReviews(1,pageable);   //只取待审核状态的书评
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            model.addAttribute("pMessage","还没有待审核的书评");
            return "admin/book_review_verify_list";
        }
        model.addAttribute("page",page);
        return "admin/book_review_verify_list";
    }

    /**
     * 书评审核页面
     */
    @GetMapping("/book_review/{id}/verify")
    public String bookReviewVerifyPage(@PathVariable Long id,
                                       Model model){
        BookReview bookReview = bookReviewService.getBookReviewAndConvert(id,1);      //只能查看待审核状态的书评
        model.addAttribute("bookReview",bookReview);
        return "admin/book_review_verify";
    }

    /**
     * 书评审核通过
     */
    @GetMapping("/book_review/verify/{id}/pass")
    public String bookReviewVerifyPass(@PathVariable Long id,
                                       HttpSession session,
                                       RedirectAttributes attributes){
        Admin admin = (Admin) session.getAttribute("admin");
        bookReviewVerifyRecordService.generateBookReviewVerifyRecord(3,id,admin);
        attributes.addFlashAttribute("pMessage","操作成功");
        return "redirect:/admin/book_review_verify_list";
    }

    /**
     * 书评审核未通过
     */
    @GetMapping("/book_review/verify/{id}/npass")
    public String bookReviewVerifyNoPass(@PathVariable Long id,
                                         HttpSession session,
                                         RedirectAttributes attributes){
        Admin admin = (Admin) session.getAttribute("admin");
        bookReviewVerifyRecordService.generateBookReviewVerifyRecord(2,id,admin);
        attributes.addFlashAttribute("pMessage","操作成功");
        return "redirect:/admin/book_review_verify_list";
    }

    /**
     * 书评审核记录列表页面
     */
    @GetMapping("/book_review_verify_record_list")
    public String bookReviewVerifyRecordList(@PageableDefault(size = 15,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable,
                                             HttpSession session,
                                             Model model){
        Admin admin = (Admin) session.getAttribute("admin");
        Page<BookReviewVerifyRecord> page = bookReviewVerifyRecordService.bookReviewVerifyRecords(admin,pageable);
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            model.addAttribute("nMessage","还没有书评审核记录，抓紧行动吧");
            return "admin/book_review_verify_record_list";
        }
        model.addAttribute("page",page);
        return "admin/book_review_verify_record_list";
    }
}
