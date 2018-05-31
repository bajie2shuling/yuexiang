package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.Book;
import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Jinzi Wu at 16:46 on 2018/5/31.
 */
public interface BookService {
    Page<Book> books(Long bookForestId, Pageable pageable);
    Book saveBook(Book book, User user, BookReview bookReview, Long bookForestId);
    Page<Book> books(User user, Pageable pageable);
}
