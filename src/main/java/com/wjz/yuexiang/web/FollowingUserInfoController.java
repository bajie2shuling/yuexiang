package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.FollowingUserInfo;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.FollowingUserInfoService;
import com.wjz.yuexiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jinzi Wu at 18:03 on 2018/5/13.
 */
@Controller
@RequestMapping("/user")
public class FollowingUserInfoController {

    @Autowired
    private FollowingUserInfoService followingUserInfoService;

    @Autowired
    private UserService userService;

    /**
     * 关注_取消关注
     */
    @PostMapping("/following_switch")
    public String followingSwitch(@RequestParam Long id,
                            HttpSession session,
                            Model model){
        User user = (User) session.getAttribute("user");
        List<Long> followingIds =(List<Long>) session.getAttribute("followingIds");
        FollowingUserInfo followingUserInfo = followingUserInfoService.saveOrDelete(id,user);
        if(followingUserInfo == null){
            followingIds.remove(id);
            session.setAttribute("followingIds",followingIds);
        }else {
            followingIds.add(id);
            session.setAttribute("followingIds",followingIds);
        }
        User userBuf = userService.getUser(id);
        user.setPassword(null);
        model.addAttribute("user",userBuf);
        return "home_page_book_review :: following";
    }

}
