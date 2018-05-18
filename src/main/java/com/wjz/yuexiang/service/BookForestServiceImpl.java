package com.wjz.yuexiang.service;


import com.wjz.yuexiang.dao.BookForestRepository;
import com.wjz.yuexiang.dao.UserRepository;
import com.wjz.yuexiang.exception.NotFoundException;
import com.wjz.yuexiang.po.BookForest;
import com.wjz.yuexiang.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jinzi Wu at 14:12 on 2018/5/17.
 */
@Service
public class BookForestServiceImpl implements BookForestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookForestRepository bookForestRepository;

    @Override
    public List<BookForest> getBookForestsByUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user =  optionalUser.orElseThrow(()-> new NotFoundException("抱歉，该用户不存在"));
        return user.getBookForests();
    }

    @Override
    public Page<BookForest> bookForests(String query, Pageable pageable) {
        Page<BookForest> page = bookForestRepository.findByLikeQuery(query,pageable);
        if(page.getTotalPages() == 0){
            return page;
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            throw new NotFoundException("很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //防止用户恶意在地址栏输入页码
        }
        return page;
    }

    /**
     *有了就删除，还没有就保存，但是在保存之前判断了是不是保存的自己
     */
    @Transactional
    @Override
    public Boolean saveOrDelete(Long bookForestId, User user) {
        Optional<BookForest> optionalBookForest = bookForestRepository.findById(bookForestId);
        BookForest bookForest = optionalBookForest.orElseThrow(()-> new NotFoundException("对不起，该书林不存在！"));
        if(user.getBookForests().contains(bookForest)){
            user.getBookForests().remove(bookForest); //删除
            return false;
        }else{
            user.getBookForests().add(bookForest);  //保存
            return true;
        }

    }
}
