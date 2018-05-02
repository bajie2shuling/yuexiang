package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jinzi Wu at 17:20 on 2018/5/2.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmailAndPassword(String email,String password);
}
