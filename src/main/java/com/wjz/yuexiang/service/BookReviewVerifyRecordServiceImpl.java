package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.BookReviewRepository;
import com.wjz.yuexiang.dao.BookReviewVerifyRecordRepository;
import com.wjz.yuexiang.exception.NotFoundException;
import com.wjz.yuexiang.po.Admin;
import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.BookReviewVerifyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Jinzi Wu at 22:43 on 2018/5/6.
 */
@Service
public class BookReviewVerifyRecordServiceImpl implements BookReviewVerifyRecordService {

    @Autowired
    private BookReviewVerifyRecordRepository bookReviewVerifyRecordRepository;

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @Transactional
    @Override
    public BookReviewVerifyRecord generateBookReviewVerifyRecord(Integer result, Long bookReviewId, Admin admin) {

        BookReview bookReview = bookReviewRepository.findByIdAndStatus(bookReviewId,1);
        if(bookReview == null){
            throw new NotFoundException("该篇书评不存在");
        }else{
            bookReview.setStatus(result);
            BookReviewVerifyRecord bookReviewVerifyRecord = new BookReviewVerifyRecord();
            bookReviewVerifyRecord.setCreateTime(new Date());
            bookReviewVerifyRecord.setBookReview(bookReview);
            bookReviewVerifyRecord.setAdmin(admin);
            bookReviewVerifyRecord.setResult(result);
            bookReviewVerifyRecord.setNickName(bookReview.getUser().getNickName());
            bookReviewVerifyRecord.setTitle(bookReview.getTitle());
            bookReviewVerifyRecord.setBookName(bookReview.getBookName());
            bookReviewVerifyRecord.setBookAuthor(bookReview.getBookAuthor());
            return bookReviewVerifyRecordRepository.save(bookReviewVerifyRecord);
        }
    }

    @Override
    public Page<BookReviewVerifyRecord> bookReviewVerifyRecords(Admin admin,Pageable pageable) {
        Page<BookReviewVerifyRecord> page = bookReviewVerifyRecordRepository.findAllByAdmin(admin,pageable);
        if(page.getTotalPages() == 0){
            return page;
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            throw new NotFoundException("很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //防止用户恶意在地址栏输入页码
        }
        return page;
    }
}
