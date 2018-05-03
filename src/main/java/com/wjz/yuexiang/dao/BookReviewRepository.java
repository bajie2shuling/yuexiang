package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jinzi Wu at 17:42 on 2018/5/3.
 */
public interface BookReviewRepository extends JpaRepository<BookReview,Long> {

}
