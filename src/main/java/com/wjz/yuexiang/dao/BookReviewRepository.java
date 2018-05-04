package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jinzi Wu at 17:42 on 2018/5/3.
 */
public interface BookReviewRepository extends JpaRepository<BookReview,Long> {

    @Transactional
    @Modifying
    @Query("update BookReview b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);
}
