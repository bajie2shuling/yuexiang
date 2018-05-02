package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.UserService;
import com.wjz.yuexiang.vo.UserSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Jinzi Wu at 17:10 on 2018/5/2.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册页面
     */
    @GetMapping("/sign_up")
    public String signUpPage(){
        return "sign_up";
    }

    /**
     * 注册处理
     */
    @PostMapping("/sign_up")
    public String signUp(@Valid UserSignUp userSignUp, BindingResult result, RedirectAttributes attributes){
        if(!userSignUp.isConfirmed()){
            result.rejectValue("confirmPwd","ConfirmError","两次密码不一致");
        }
        if(result.hasErrors()){
            return "redirect:/sign_up";
        }
        User user = userService.saveUser(userSignUp.convertToUser());
        user.setPassword(null);
        attributes.addFlashAttribute("user",user);
        return "redirect:/sign_in";
    }

    /**
     * 登录页面
     */
    @GetMapping("/sign_in")
    public String signInPage(){
        return "sign_in";
    }

    /**
     * 登录处理
     */
    @PostMapping("/sign_in")
    public String signIn(@RequestParam String email,
                         @RequestParam String password,
                         HttpSession session,
                         RedirectAttributes attributes){
        User user = userService.checkUser(email,password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "index";
        }else {
            attributes.addFlashAttribute("message","邮箱和密码错误");
            return "redirect:/sign_in";
        }

    }
}
