package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.BookReviewRepository;
import com.wjz.yuexiang.dao.BookReviewVerifyRecordRepository;
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
            return null;
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
            return bookReviewVerifyRecordRepository.save(bookReviewVerifyRecord);
        }
    }

    @Override
    public Page<BookReviewVerifyRecord> bookReviewVerifyRecords(Admin admin,Pageable pageable) {
        return bookReviewVerifyRecordRepository.findAllByAdmin(admin,pageable);
    }
}
