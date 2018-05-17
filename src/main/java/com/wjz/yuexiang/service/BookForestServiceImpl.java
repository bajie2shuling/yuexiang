package com.wjz.yuexiang.service;


import com.wjz.yuexiang.dao.UserRepository;
import com.wjz.yuexiang.exception.NotFoundException;
import com.wjz.yuexiang.po.BookForest;
import com.wjz.yuexiang.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jinzi Wu at 14:12 on 2018/5/17.
 */
@Service
public class BookForestServiceImpl implements BookForestService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BookForest> getBookForestsByUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user =  optionalUser.orElseThrow(()-> new NotFoundException("抱歉，该用户不存在"));
        return user.getBookForests();
    }
}
