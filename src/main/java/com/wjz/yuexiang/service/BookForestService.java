package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.BookForest;

import java.util.List;

/**
 * Created by Jinzi Wu at 14:09 on 2018/5/17.
 */
public interface BookForestService {
    List<BookForest> getBookForestsByUser(Long userId);
}
