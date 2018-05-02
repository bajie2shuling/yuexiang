package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.User;

/**
 * Created by Jinzi Wu at 17:19 on 2018/5/2.
 */
public interface UserService {
    User checkUser(String email,String password);
    User saveUser(User user);
}
