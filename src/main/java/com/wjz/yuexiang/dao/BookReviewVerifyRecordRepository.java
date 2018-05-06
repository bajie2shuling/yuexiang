package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.Admin;
import com.wjz.yuexiang.po.BookReviewVerifyRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Jinzi Wu at 22:41 on 2018/5/6.
 */
public interface BookReviewVerifyRecordRepository extends JpaRepository<BookReviewVerifyRecord,Long> {

    Page<BookReviewVerifyRecord> findAllByAdmin(Admin admin, Pageable pageable);
}
