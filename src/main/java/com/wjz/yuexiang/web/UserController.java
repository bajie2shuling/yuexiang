package com.wjz.yuexiang.web;

import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.service.UserService;
import com.wjz.yuexiang.vo.UserSignIn;
import com.wjz.yuexiang.vo.UserSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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
    public String signUpPage(Model model){
        UserSignUp userSignUp = new UserSignUp();
        model.addAttribute("userSignUp",userSignUp);
        return "sign_up";
    }

    /**
     * 注册处理
     */
    @PostMapping("/sign_up")
    public String signUp(@Valid UserSignUp userSignUp,
                         BindingResult result,
                         Model model){
        if(!userSignUp.isConfirmed()){
            result.rejectValue("confirmPwd","ConfirmError","两次密码不一致");
        }
        if(result.hasErrors()){
            model.addAttribute("userSignUp",userSignUp);
            return "sign_up";
        }
        userService.saveUser(userSignUp.convertToUser());
        return "redirect:/user/sign_in";
    }

    /**
     * 登录页面
     */
    @GetMapping("/sign_in")
    public String signInPage(Model model){
        UserSignIn userSignIn= new UserSignIn();
        model.addAttribute("userSignIn",userSignIn);
        return "sign_in";
    }

    /**
     * 登录处理
     */
    @PostMapping("/sign_in")
    public String signIn(@Valid UserSignIn userSignIn,
                         BindingResult result,
                         HttpSession session,
                         RedirectAttributes attributes,Model model){
        if(result.hasErrors()){
            model.addAttribute("userSignIn",userSignIn);
            return "sign_in";
        }
        User user = userService.checkUser(userSignIn.getEmail(),userSignIn.getPassword());
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "index";
        }else {
            attributes.addFlashAttribute("loginMessage","邮箱和密码错误");
//            attributes.addFlashAttribute("userSignIn",userSignIn);
            return "redirect:/user/sign_in";
        }

    }
}
