package com.wjz.yuexiang.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jinzi Wu at 20:53 on 2018/5/3.
 */
@Controller
@RequestMapping("/user")
public class IndexController {

    @GetMapping("/index")
    public String indexPage(){
        return "index";
    }
}
