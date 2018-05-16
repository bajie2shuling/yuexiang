package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.Admin;
import com.wjz.yuexiang.po.VerifyRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jinzi Wu at 22:41 on 2018/5/6.
 */
public interface VerifyRecordRepository extends JpaRepository<VerifyRecord,Long> {

    Page<VerifyRecord> findAllByAdmin(Admin admin, Pageable pageable);
}
