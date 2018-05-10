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
     * 注册
     */
    @PostMapping("/sign_up")
    public String signUp(@Valid UserSignUp userSignUp,
                         BindingResult result,
                         RedirectAttributes attributes,
                         Model model){

        if(userService.isNickNameExist(userSignUp.getNickName())){
            result.rejectValue("nickName","nickNameError","用户名已存在");
        }
        if(userService.isEmailExist(userSignUp.getEmail())){
            result.rejectValue("email","emailError","邮箱已注册");
        }
        if(!userSignUp.isConfirmed()){
            result.rejectValue("confirmPwd","ConfirmError","两次密码不一致");
        }
        if(result.hasErrors()){
            model.addAttribute("userSignUp",userSignUp);
            return "sign_up";       //注册失败
        }
        userService.saveUser(userSignUp.convertToUser());
        attributes.addFlashAttribute("signUpMessage","恭喜你，注册成功");
        return "redirect:/user/sign_in";        //注册成功
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
     * 登录
     */
    @PostMapping("/sign_in")
    public String signIn(@Valid UserSignIn userSignIn,
                         BindingResult result,
                         HttpSession session,
                         Model model){
        if(result.hasErrors()){
            model.addAttribute("userSignIn",userSignIn);
            return "sign_in";  //登录失败(数据输入不合法)
        }
        User user = userService.getUser(userSignIn.getEmail(),userSignIn.getPassword());        //登录验证
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/user/index";       //登陆成功
        }else {
            model.addAttribute("signInMessage","邮箱或密码输入错误");
            model.addAttribute("userSignIn",userSignIn);
            return "sign_in";       //登录失败
        }

    }

    /**
     * 注销登录
     */
    @GetMapping("/sign_out")
    public String signOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/user/sign_in";
    }
}
