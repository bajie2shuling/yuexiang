package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.BookForest;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.BookForestService;
import com.wjz.yuexiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jinzi Wu at 22:55 on 2018/5/17.
 */
@Controller
@RequestMapping("/user")
public class BookForestController {


    @Autowired
    private UserService userService;

    @Autowired
    private BookForestService bookForestService;

    @GetMapping("/book_forests")
    public String bookForestsPage(HttpSession session,
                                  Model model) {
        User user = (User) session.getAttribute("user");
        userService.getUser(user.getId());
        List<BookForest> bookForests = bookForestService.getBookForestsByUser(user.getId());
        if (bookForests == null || bookForests.isEmpty()) {
            model.addAttribute("nMessage", "你还没有加入任何书林，赶紧去加入吧！");
            model.addAttribute("bookForests", bookForests);
            return "book_forests";
        }
        model.addAttribute("bookForests", bookForests);
        return "book_forests";
    }
}
