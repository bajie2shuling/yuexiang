package com.wjz.yuexiang.web.admin;

import com.wjz.yuexiang.po.Admin;
import com.wjz.yuexiang.service.AdminService;
import com.wjz.yuexiang.vo.AdminSignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Jinzi Wu at 19:34 on 2018/5/5.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     *管理员登录页面
     */
    @GetMapping("/sign_in")
    public String signInPage(Model model){
        AdminSignIn adminSignIn = new AdminSignIn();
        model.addAttribute("adminSignIn",adminSignIn);
        return "admin/sign_in";
    }

    /**
     * 管理员登录
     */
    @PostMapping("/sign_in")
    public String signIn(@Valid AdminSignIn adminSignIn,
                         BindingResult result,
                         HttpSession session,
                         Model model){
        if(result.hasErrors()){
            model.addAttribute("adminSignIn",adminSignIn);
            return "admin/sign_in";
        }
        Admin admin = adminService.signInCheckAdmin(adminSignIn.getIdNumber(),adminSignIn.getPassword());
        if(admin == null){
            model.addAttribute("adminSignIn",adminSignIn);
            model.addAttribute("signInMessage","用户名或密码输入错误");
            return "admin/sign_in";
        }else {
            admin.setPassword(null);
            session.setAttribute("admin",admin);
            return "redirect:/admin/book_review_verify_list";
        }
    }

    /**
     * 管理员注销
     */
    @GetMapping("/sign_out ")
    public String signOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin/sign_in";
    }
}
