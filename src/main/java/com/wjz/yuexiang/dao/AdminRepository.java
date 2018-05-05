package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jinzi Wu at 20:51 on 2018/5/5.
 */
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByIdNumberAndPassword(String idNumber,String password);
}
