package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.FollowingUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 关注
     */
    @GetMapping("/{id}/following")
    public String following(@PathVariable Long id,
                            HttpSession session){
        User user = (User) session.getAttribute("user");
        followingUserInfoService.generateFollowingUserInfo(id,user);
        List<Long> followingIds =(List<Long>) session.getAttribute("followingIds");
        followingIds.add(id);
        session.setAttribute("followingIds",followingIds);
        return "home_page_book_review :: following";
    }
}
