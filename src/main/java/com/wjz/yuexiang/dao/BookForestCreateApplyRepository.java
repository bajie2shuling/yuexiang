package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.BookForestCreateApply;
import com.wjz.yuexiang.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jinzi Wu at 21:08 on 2018/5/17.
 */
public interface BookForestCreateApplyRepository extends JpaRepository<BookForestCreateApply,Long> {

    BookForestCreateApply findByIdAndUser(Long id,User user);
    void deleteByIdAndUser(Long id,User user);
    Page<BookForestCreateApply> findAllByUser(User user, Pageable pageable);
    Page<BookForestCreateApply> findAllByStatus(Integer status, Pageable pageable);
    BookForestCreateApply findByIdAndStatus(Long id,Integer status);
}
