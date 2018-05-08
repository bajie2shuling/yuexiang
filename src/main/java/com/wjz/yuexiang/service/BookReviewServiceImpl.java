package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.BookReviewRepository;
import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.BookReviewVerifyRecord;
import com.wjz.yuexiang.po.User;
import com.wjz.yuexiang.utils.MarkdownUtils;
import com.wjz.yuexiang.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by Jinzi Wu at 17:44 on 2018/5/3.
 */
@Service
public class BookReviewServiceImpl implements BookReviewService {

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @Transactional
    @Override
    public BookReview saveBookReview(BookReview bookReview) {
        bookReview.setViews(0);  //创将时初始化一下后面才能进行加1操作
        return bookReviewRepository.save(bookReview);
    }

    @Transactional
    @Override
    public BookReview updateBookReview(Long id,User user, BookReview b) {
        BookReview bookReview = bookReviewRepository.findByIdAndUser(id,user);
        if(bookReview == null){
            return null;            //为空就交给上层，让上层处理异常
        }else{
            BeanUtils.copyProperties(b,bookReview, MyBeanUtils.getNullPropertyNames(b));
            return bookReviewRepository.save(bookReview);
        }
    }

    /**
     * 书评获取时的结果分发器
     * @param bookReview
     * @return
     */
    private BookReview bookReviewDispatcher(BookReview bookReview){
        if(bookReview == null){
            return null;              //为空就交给上层，让上层处理异常
        }else{
            String content = bookReview.getContent();
            bookReview.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

            return bookReview;
        }
    }

    @Override
    public BookReview getBookReviewAndConvert(Long id,Long userId) {
        BookReview bookReview = bookReviewRepository.findByIdAndUserId(id,userId);
        return bookReviewDispatcher(bookReview);
    }

    /**
     * 根据书评状态和id查询查询书评
     * @param id
     * @param status
     * @return
     */
    @Override
    public BookReview getBookReviewAndConvert(Long id, Integer status) {
        BookReview bookReview = bookReviewRepository.findByIdAndStatus(id,status);
        return bookReviewDispatcher(bookReview);
    }


    @Override
    public BookReview getBookReview(Long id, Long userId) {
        return bookReviewRepository.findByIdAndUserId(id,userId);              //不管是否为空直接交给上层，让上层处理异常
    }

    /**
     * 根据用户ID分页查询书评
     * @param user
     * @param pageable
     * @return
     */
    @Override
    public Page<BookReview> bookReviews(User user, Pageable pageable) {
        //该方法也行，不过大材小用了
        //return bookReviewRepository.findAll((root, cq, cb) -> cb.equal(root.<Long>get("user").get("id"),userId),pageable);
        return bookReviewRepository.findAllByUser(user,pageable);
    }

    @Override
    public Page<BookReview> bookReviews(Pageable pageable) {
        return bookReviewRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Boolean deleteSelfBookReview(Long id, User user) {
        BookReview bookReview = bookReviewRepository.findByIdAndUser(id,user);
        if(bookReview == null){
            return false;
        }
        for(BookReviewVerifyRecord bookReviewVerifyRecord : bookReview.getBookReviewVerifyRecords()){
            bookReviewVerifyRecord.setBookReview(null);
        }
        bookReviewRepository.deleteByIdAndUser(id,user);
        return true;
    }

    /**
     * 根据书评状态查询
     * @param status
     * @param pageable
     * @return
     */
    @Override
    public Page<BookReview> bookReviews(Integer status, Pageable pageable) {
        //该方法也行，不过大材小用了
        // return bookReviewRepository.findAll((root, cq, cb) -> cb.equal(root.<Integer>get("status"),status),pageable);
        return bookReviewRepository.findAllByStatus(status,pageable);
    }


}
