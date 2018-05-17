package com.wjz.yuexiang.web;


import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jinzi Wu at 15:22 on 2018/5/17.
 */
@Controller
@RequestMapping("/user")
public class FollowingController {

    @Autowired
    private UserService userService;

    @GetMapping("/following")
    public String followingPage(HttpSession session,
                                Model model) {
        List<Long> followingIds = (List<Long>) session.getAttribute("followingIds");
        List<User> users = userService.users(followingIds);
        if(users == null || users.isEmpty()){
            model.addAttribute("nMessage","你还没有关注任何人");
            model.addAttribute("users",users);
            return "following";
        }
        model.addAttribute("users",users);
        return "following";
    }
}
