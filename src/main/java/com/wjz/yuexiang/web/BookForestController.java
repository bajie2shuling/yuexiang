package com.wjz.yuexiang.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jinzi Wu at 18:01 on 2018/5/17.
 */
@Controller
@RequestMapping("/user")
public class BookForestController {

    @GetMapping("bookforest/apply")
    public String bookForestApplyPage(){
        
    }
}
