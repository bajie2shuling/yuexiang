package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.UserRepository;
import com.wjz.yuexiang.exception.NotFoundException;
import com.wjz.yuexiang.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;


/**
 * Created by Jinzi Wu at 17:24 on 2018/5/2.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String email, String password) {

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

    @Transactional
    @Override
    public User saveUser(User user) {
        user.setCreateTime(new Date());
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(()-> new NotFoundException("抱歉，该用户不存在"));
    }
}
