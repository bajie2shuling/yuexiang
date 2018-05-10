package com.wjz.yuexiang.web;

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
 * Created by Jinzi Wu at 20:53 on 2018/5/3.
 */
@Controller
@RequestMapping("/user")
public class IndexController {

    @Autowired
    private BookReviewService bookReviewService;

    @GetMapping("/index")
    public String indexPage(@PageableDefault(size = 5,sort = {"views"},direction = Sort.Direction.DESC)Pageable pageable,
                            Model model){
        Page<BookReview> page = bookReviewService.bookReviews(3,pageable);  //查看审核通过的
        if(page.getTotalPages() == 0){
            model.addAttribute("page",page);   //前端模版要取page
            model.addAttribute("nMessage","抱歉，还没有书评");
            return "index";
        }
        model.addAttribute("page",page);
        return "index";
    }
}
