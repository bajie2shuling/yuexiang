package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.BookReviewRepository;
import com.wjz.yuexiang.exception.NotFoundException;
import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.utils.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        bookReview.setCreateTime(new Date());   //创建时间
        bookReview.setViews(0);
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
            b.setUpdateTime(new Date());   //修改时间
            BeanUtils.copyProperties(b,bookReview);
            return bookReviewRepository.save(bookReview);
        }
    }


    @Transactional
    @Override
    public BookReview getBookReviewAndConvert(Long id) {
        Optional<BookReview> bookReviewOptional = bookReviewRepository.findById(id);
        if(!bookReviewOptional.isPresent()){
            throw new NotFoundException("该书评不存在");
        }else{
            BookReview bookReview = new BookReview();
            BookReview b = bookReviewOptional.get();
            BeanUtils.copyProperties(b,bookReview);     //防止事务误操作，将数据库中取出的数据赋给新的对象

            String content = bookReview.getContent();
            bookReview.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

            bookReviewRepository.updateViews(id);

            return bookReview;
        }
    }
}
