package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.BookReviewRepository;
import com.wjz.yuexiang.exception.NotFoundException;
import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Created by Jinzi Wu at 17:44 on 2018/5/3.
 */
@Service
public class BookReviewServiceImpl implements BookReviewService {

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @Override
    public BookReview saveBookReview(BookReview bookReview) {
        return bookReviewRepository.save(bookReview);
    }

    @Transactional
    @Override
    public BookReview updateBookReview(Long id, BookReview b) {
        Optional<BookReview> bookReviewOptional = bookReviewRepository.findById(id);
        if(!bookReviewOptional.isPresent()){
            throw new NotFoundException("该书评不存在");
        }else{
            BookReview bookReview = bookReviewOptional.get();
            BeanUtils.copyProperties(b,bookReview, MyBeanUtils.getNullPropertyNames(b));
            return bookReviewRepository.save(bookReview);
        }
    }
}
