package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.User;

/**
 * Created by Jinzi Wu at 17:19 on 2018/5/2.
 */
public interface UserService {
    User signInCheckUser(String email,String password);     //登录验证邮箱和密码
    Boolean isEmailExist(String email);
    Boolean isNickNameExist(String nickName);
    User saveUser(User user);
}
