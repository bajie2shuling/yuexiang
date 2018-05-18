package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.BookForest;
import com.wjz.yuexiang.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Jinzi Wu at 14:09 on 2018/5/17.
 */
public interface BookForestService {
    List<BookForest> getBookForestsByUser(Long userId);
    Page<BookForest> bookForests(String query, Pageable pageable);
    Boolean saveOrDelete(Long bookForestId, User user);
}
