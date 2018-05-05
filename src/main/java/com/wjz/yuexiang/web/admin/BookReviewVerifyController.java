package com.wjz.yuexiang.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Jinzi Wu at 21:25 on 2018/5/5.
 */
@Controller("/admin")
public class BookReviewVerifyController {

    @GetMapping("/book_review_verify")
    public String bookReviewVerifyPage(){
        return "book_review_verify";
    }
}
