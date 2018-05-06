package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.Admin;
import com.wjz.yuexiang.po.BookReviewVerifyRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Jinzi Wu at 22:43 on 2018/5/6.
 */
public interface BookReviewVerifyRecordService {
    BookReviewVerifyRecord generateBookReviewVerifyRecord(Integer result, Long bookReviewId, Admin admin);
    Page<BookReviewVerifyRecord> bookReviewVerifyRecords(Admin admin, Pageable pageable);
}
