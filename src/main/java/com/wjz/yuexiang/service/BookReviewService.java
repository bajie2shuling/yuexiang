package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.BookReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Jinzi Wu at 17:43 on 2018/5/3.
 */
public interface BookReviewService {
    BookReview saveBookReview(BookReview bookReview);
    BookReview updateBookReview(Long id,BookReview bookReview);
    BookReview getBookReviewAndConvert(Long id);
    Page<BookReview> bookReviews(Long userId, Pageable pageable);
}
