package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.Admin;
import com.wjz.yuexiang.po.VerifyRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Jinzi Wu at 22:43 on 2018/5/6.
 */
public interface VerifyRecordService {
    VerifyRecord generateBookReviewVerifyRecord(Integer verifyResult, Integer bookReviewStatus, Long bookReviewId, Admin admin);
    Page<VerifyRecord> verifyRecords(Admin admin, Pageable pageable);
}
