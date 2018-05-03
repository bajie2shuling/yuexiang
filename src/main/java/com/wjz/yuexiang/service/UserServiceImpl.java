package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.UserRepository;
import com.wjz.yuexiang.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Jinzi Wu at 17:24 on 2018/5/2.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signInCheckUser(String email, String password) {

        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public Boolean isEmailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user == null ? false : true;
    }

    @Override
    public Boolean isNickNameExist(String nickName) {
        User user = userRepository.findByNickName(nickName);
        return user == null ? false : true;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
