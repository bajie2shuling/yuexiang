package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.BookReviewRepository;
import com.wjz.yuexiang.exception.NotFoundException;
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
import java.util.Date;
import java.util.Optional;


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
        bookReview.setCreateTime(new Date());   //创建时间
        bookReview.setViews(0);
        return bookReviewRepository.save(bookReview);
    }

    @Transactional
    @Override
    public BookReview updateBookReview(Long id, BookReview b) {
        Optional<BookReview> bookReviewOptional = bookReviewRepository.findById(id);
        if(!bookReviewOptional.isPresent()){
            throw new NotFoundException("该书评不存在");   //此处在直接返回空对象在上层处理更人性化
        }else{
            BookReview bookReview = bookReviewOptional.get();
            b.setUpdateTime(new Date());   //修改时间
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
            return bookReview;              //为空就交给上层，让上层处理异常
        }else{
            BookReview b = new BookReview();
            BeanUtils.copyProperties(bookReview,b);     //防止事务误操作，将数据库中取出的数据赋给新的对象

            String content = b.getContent();
            b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

            return b;
        }
    }

    @Transactional
    @Override
    public BookReview getBookReviewAndConvert(Long id,Long userId) {
        BookReview bookReview = bookReviewRepository.findByIdAndUserId(id,userId);
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

    @Transactional
    @Override
    public void deleteSelfBookReview(Long id, User user) {
        bookReviewRepository.deleteByIdAndUser(id,user);
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
}
