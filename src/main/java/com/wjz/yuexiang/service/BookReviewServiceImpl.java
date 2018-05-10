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
        bookReview.setPublishTime(new Date());
        return bookReviewRepository.save(bookReview);
    }

    @Transactional
    @Override
    public BookReview updateBookReview(Long id,User user, BookReview b) {
        BookReview bookReview = bookReviewRepository.findByIdAndUser(id,user);
        if(bookReview == null){
            throw new NotFoundException("很遗憾，该篇书评不存在！");
        }else{
            BeanUtils.copyProperties(b,bookReview, MyBeanUtils.getNullPropertyNames(b));
            bookReview.setPublishTime(new Date());
            return bookReviewRepository.save(bookReview);
        }
    }


    @Override
    public BookReview getBookReviewAndConvert(Long id,Long userId) {
        BookReview bookReview = bookReviewRepository.findByIdAndUserId(id,userId);
        if(bookReview == null){
            throw new NotFoundException("很遗憾，该篇书评不存在！");          //防止恶意用户在地址栏随意输入id
        }
        String content = bookReview.getContent();
        bookReview.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return bookReview;
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
        if(bookReview == null){
            throw new NotFoundException("该篇书评不存在");       //防止用户恶意在地址栏输入书评id
        }
        String content = bookReview.getContent();
        bookReview.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return bookReview;
    }

    /**
     * 根据书评状态和id查询查询书评，并更新浏览次数
     * @param id
     * @param status
     * @return
     */
    @Transactional
    @Override
    public BookReview getBookReviewSetViewsAndConvert(Long id, Integer status) {
        BookReview bookReview = bookReviewRepository.findByIdAndStatus(id,status);
        if(bookReview == null){
            throw new NotFoundException("很遗憾,该篇书评不存在");       //防止用户恶意在地址栏输入书评id
        }else{
            bookReviewRepository.updateViews(id);
            BookReview b = new BookReview();
            BeanUtils.copyProperties(bookReview,b);
            String content = b.getContent();
            b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

            return b;
        }
    }


    @Override
    public BookReview getBookReview(Long id, Long userId) {
        BookReview bookReview = bookReviewRepository.findByIdAndUserId(id,userId);
        if(bookReview == null){
            throw new NotFoundException("很遗憾，该篇书评不存在！");         //防止恶意用户在地址栏随意输入id
        }
        return bookReview;
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
        Page<BookReview> page = bookReviewRepository.findAllByUser(user,pageable);
        if(page.getTotalPages() == 0){
            return page;
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            throw new NotFoundException("很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //spring从0页开始
        }
        return page;
    }


    @Transactional
    @Override
    public void deleteSelfBookReview(Long id, User user) {
        BookReview bookReview = bookReviewRepository.findByIdAndUser(id,user);
        if(bookReview == null){
            throw new NotFoundException("很遗憾,该篇书评不存在!");
        }
        for(BookReviewVerifyRecord bookReviewVerifyRecord : bookReview.getBookReviewVerifyRecords()){
            bookReviewVerifyRecord.setBookReview(null);
        }
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
        Page<BookReview> page = bookReviewRepository.findAllByStatus(status,pageable);
        if(page.getTotalPages() == 0){
            return page;
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            throw new NotFoundException("很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //防止用户恶意在地址栏输入页码
        }
        return page;
    }

    /**
     * 根据用户和书评状态查询
     * @param user
     * @param status
     * @param pageable
     * @return
     */
    @Override
    public Page<BookReview> bookReviews(User user, Integer status, Pageable pageable) {

        Page<BookReview> page = bookReviewRepository.findAllByUserAndStatus(user,status,pageable);
        if(page.getTotalPages() == 0){
            return page;
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            throw new NotFoundException("很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //spring从0页开始
        }
        return page;
    }
}
