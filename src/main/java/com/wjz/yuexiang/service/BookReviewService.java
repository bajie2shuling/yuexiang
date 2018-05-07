package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Jinzi Wu at 17:43 on 2018/5/3.
 */
public interface BookReviewService {
    BookReview saveBookReview(BookReview bookReview);
    BookReview updateBookReview(Long id,BookReview bookReview);

    Page<BookReview> bookReviews(User user, Pageable pageable);
    Page<BookReview> bookReviews(Integer status, Pageable pageable);

    BookReview getBookReviewAndConvert(Long id,Long userId);  //根据用户ID和文章ID一起查询（限定用户只能预览自己的书评）
    BookReview getBookReview(Long id,Long userId);  //根据用户ID和文章ID一起查询（限定用户只能编辑自己的书评）

    void deleteSelfBookReview(Long id,User user);  //根据用户ID和文章ID一起查询（限定用户只能删除自己的书评）

    BookReview getBookReviewAndConvert(Long id,Integer status);


}
