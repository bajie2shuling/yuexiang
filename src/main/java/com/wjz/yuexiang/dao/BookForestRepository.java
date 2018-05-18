package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.BookForest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Jinzi Wu at 13:56 on 2018/5/17.
 */
public interface BookForestRepository extends JpaRepository<BookForest,Long> {

    @Query("select bf from BookForest bf where bf.name like ?1")
    Page<BookForest> findByLikeQuery(String query, Pageable pageable);
}
