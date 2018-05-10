package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.BookReviewService;
import com.wjz.yuexiang.vo.BookReviewPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Jinzi Wu at 17:20 on 2018/5/3.
 */
@Controller
@RequestMapping("/user")
public class SelfBookReviewController {

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
    public String bookReviewPost(@Valid BookReviewPost bookReviewPost,
                                 BindingResult result,
                                 RedirectAttributes attributes,
                                 Model model,
                                 HttpSession session){
        User user = (User)session.getAttribute("user");
        BookReview bookReview = bookReviewPost.convertToBookReview();
        if(result.hasErrors()){
            model.addAttribute("bookReview",bookReview);
            return "book_review_edit";  //数据输入不合法
        }
        if(bookReview.getId() == null){
            bookReview.setUser(user);
            bookReviewService.saveBookReview(bookReview);
        }else{
            bookReviewService.updateBookReview(bookReview.getId(),user,bookReview);
        }
        attributes.addFlashAttribute("message","书评操作成功");
        return "redirect:/user/book_review_list";
    }

    /**
     * 书评列表页面
     */
    @GetMapping("/book_review_list")
    public String bookReviewList(@PageableDefault(size = 15,sort = {"publishTime"},direction = Sort.Direction.DESC) Pageable pageable,
                                 HttpSession session,
                                 Model model){
        User user = (User) session.getAttribute("user");
        Page<BookReview> page = bookReviewService.bookReviews(user,pageable);
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            model.addAttribute("lostMessage","还没有书评,赶紧拿起笔行动吧！");
            return "book_review_list";
        }
        model.addAttribute("page",page);
        return "book_review_list";
    }

    /**
     * 书评修改页面
     */
    @GetMapping("/book_review/{id}/change")
    public String bookReviewChange(@PathVariable Long id,
                                   HttpSession session,
                                   Model model){
        User user = (User) session.getAttribute("user");
        BookReview bookReview = bookReviewService.getBookReview(id,user.getId());
        model.addAttribute("bookReview",bookReview);
        return "book_review_edit";
    }

    /**
     * 书评详情预览页面
     */
    @GetMapping("/book_review/{id}/preview")
    public String bookReviewPage(@PathVariable Long id,
                                 HttpSession session,
                                 Model model){
        User user = (User) session.getAttribute("user");
        BookReview bookReview = bookReviewService.getBookReviewAndConvert(id,user.getId());
        model.addAttribute("bookReview",bookReview);
        return "book_review";
    }

    /**
     * 删除书评
     */
    @GetMapping("/book_review/{id}/delete")
    public String bookReviewPage(@PathVariable Long id,
                                 HttpSession session,
                                 RedirectAttributes attributes){
        User user = (User) session.getAttribute("user");
        bookReviewService.deleteSelfBookReview(id,user);
        attributes.addFlashAttribute("message","删除书评成功！");
        return "redirect:/user/book_review_list";
    }

}
