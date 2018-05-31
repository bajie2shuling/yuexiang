package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.Book;
import com.wjz.yuexiang.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Jinzi Wu at 16:59 on 2018/5/31.
 */
public interface BookRepository extends JpaRepository<Book,Long> {

    Page<Book> findAllByBookForestId(Long bookForestId,Pageable pageable);
    Page<Book> findAllByUser(User user, Pageable pageable);
}
