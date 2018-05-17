package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.BookForestCreateApplyRepository;
import com.wjz.yuexiang.exception.NotFoundException;
import com.wjz.yuexiang.po.BookForestCreateApply;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.po.VerifyRecord;
import com.wjz.yuexiang.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Jinzi Wu at 21:10 on 2018/5/17.
 */
@Service
public class BookForestCreateApplyServiceImpl implements BookForestCreateApplyService {

    @Autowired
    private BookForestCreateApplyRepository bookForestCreateApplyRepository;

    @Transactional
    @Override
    public BookForestCreateApply saveBookForestCreateApply(BookForestCreateApply bookForestCreateApply) {
        bookForestCreateApply.setCreateTime(new Date());
        bookForestCreateApply.setStatus(0);
        return bookForestCreateApplyRepository.save(bookForestCreateApply);
    }

    @Transactional
    @Override
    public BookForestCreateApply updateBookForestCreateApply(Long id, User user, BookForestCreateApply b){
        BookForestCreateApply bookForestCreateApply = bookForestCreateApplyRepository.findByIdAndUser(id,user);
        if(bookForestCreateApply == null){
            throw new NotFoundException("很遗憾，该条书林创建申请不存在！");
        }else {
            BeanUtils.copyProperties(b,bookForestCreateApply, MyBeanUtils.getNullPropertyNames(b));
            bookForestCreateApply.setCreateTime(new Date());
            bookForestCreateApply.setStatus(0);
            return bookForestCreateApplyRepository.save(bookForestCreateApply);
        }
    }

    @Transactional
    @Override
    public void deleteBookForestCreateApply(Long id, User user) {
        BookForestCreateApply bookForestCreateApply = bookForestCreateApplyRepository.findByIdAndUser(id,user);
        if(bookForestCreateApply == null){
            throw new NotFoundException("很遗憾，该条书林创建申请不存在！");
        }
        for(VerifyRecord verifyRecord : bookForestCreateApply.getVerifyRecords()){
            verifyRecord.setBookForestCreateApply(null);
        }
        bookForestCreateApplyRepository.deleteByIdAndUser(id,user);
    }

    @Override
    public Page<BookForestCreateApply> bookForestCreateApplies(User user, Pageable pageable) {
        Page<BookForestCreateApply> page = bookForestCreateApplyRepository.findAllByUser(user,pageable);
        if(page.getTotalPages() == 0){
            return page;
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            throw new NotFoundException("很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //spring从0页开始
        }
        return page;
    }

    @Override
    public BookForestCreateApply getBookForestCreateApply(Long id, User user) {
        BookForestCreateApply bookForestCreateApply = bookForestCreateApplyRepository.findByIdAndUser(id,user);
        if(bookForestCreateApply == null){
            throw new NotFoundException("很遗憾，该条书林创建申请不存在！");         //防止恶意用户在地址栏随意输入id
        }
        return bookForestCreateApply;
    }

    @Override
    public Page<BookForestCreateApply> bookForestCreateApplies(Integer status, Pageable pageable) {
        Page<BookForestCreateApply> page = bookForestCreateApplyRepository.findAllByStatus(status,pageable);
        if(page.getTotalPages() == 0){
            return page;
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            throw new NotFoundException("很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //防止用户恶意在地址栏输入页码
        }
        return page;
    }
}
