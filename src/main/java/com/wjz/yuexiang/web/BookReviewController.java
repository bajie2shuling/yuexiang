package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

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
            bookReviewService.saveBookReview(bookReview);
        }else{
            bookReviewService.updateBookReview(bookReview.getId(),bookReview);
        }
        attributes.addFlashAttribute("message","书评操作成功");
        return "redirect:/user/book_review_list";
    }

    /**
     * 书评列表页面
     */
    @GetMapping("/book_review_list")
    public String bookReviewList(@PageableDefault(size = 8,sort = {"updateTime","createTime"},direction = Sort.Direction.DESC) Pageable pageable,
                                 HttpSession session,
                                 RedirectAttributes attributes,
                                 Model model){
        User user = (User) session.getAttribute("user");
        Page<BookReview> page = bookReviewService.bookReviews(user.getId(),pageable);
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            attributes.addFlashAttribute("lostMessage","很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //spring从0页开始
            return "redirect:/user/book_review_list";
        }
        model.addAttribute("page",page);
        return "book_review_list";
    }

    /**
     * 书评修改
     */
    @GetMapping("/book_review/{id}/change")
    public String bookReviewChange(@PathVariable Long id,
                                   HttpSession session,
                                   RedirectAttributes attributes,
                                   Model model){
        User user = (User) session.getAttribute("user");
        BookReview bookReview = bookReviewService.getSelfBookReview(id,user.getId());
        if(bookReview == null){
            attributes.addFlashAttribute("lostMessage","很遗憾，该篇书评不存在！");         //防止恶意用户在地址栏随意输入id
            return "redirect:/user/book_review_list";
        }else{
            model.addAttribute("bookReview",bookReview);
            return "book_review_edit";
        }
    }

    /**
     * 书评详情预览
     */
    @GetMapping("/book_review/{id}/preview")
    public String bookReviewPage(@PathVariable Long id,
                                 HttpSession session,
                                 RedirectAttributes attributes,
                                 Model model){
        User user = (User) session.getAttribute("user");
        BookReview bookReview = bookReviewService.getSelfBookReviewAndConvert(id,user.getId());
        if(bookReview == null){
            attributes.addFlashAttribute("lostMessage","很遗憾，该篇书评不存在！");          //防止恶意用户在地址栏随意输入id
            return "redirect:/user/book_review_list";
        }else{
            model.addAttribute("bookReview",bookReview);
            return "book_review";
        }
    }

    /**
     * 删除书评
     */
    @GetMapping("/book_review/{id}/delete")
    public String bookReviewPage(@PathVariable Long id,
                                 HttpSession session,
                                 RedirectAttributes attributes){
        User user = (User) session.getAttribute("user");
        bookReviewService.deleteSelfBookReview(id,user.getId());
        attributes.addFlashAttribute("message","删除书评成功！");
        return "redirect:/user/book_review_list";
    }
}
