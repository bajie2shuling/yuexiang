package com.wjz.yuexiang.vo;

import com.wjz.yuexiang.po.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by Jinzi Wu at 17:53 on 2018/5/2.
 */
public class UserSignUp {

    @NotBlank(message = "用户名不能为空")
    private String nickname;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Length(min = 6,message = "密码至少需要六位")
    private String password;

    private String confirmPwd;

    public UserSignUp() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public boolean isConfirmed(){
        return password.equals(confirmPwd) ? true : false;
    }

    public User convertToUser(){
        User user = new User();
        BeanUtils.copyProperties(this,user);
        return user;
    }
}
