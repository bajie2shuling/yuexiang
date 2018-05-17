package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.BookForestCreateApply;
import com.wjz.yuexiang.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Jinzi Wu at 21:09 on 2018/5/17.
 */
public interface BookForestCreateApplyService {
    BookForestCreateApply saveBookForestCreateApply(BookForestCreateApply bookForestCreateApply);
    BookForestCreateApply updateBookForestCreateApply(Long id, User user, BookForestCreateApply bookForestCreateApply);
    void deleteBookForestCreateApply(Long id,User user);

    Page<BookForestCreateApply> bookForestCreateApplies(User user, Pageable pageable);

    BookForestCreateApply getBookForestCreateApply(Long id,User user);
}
