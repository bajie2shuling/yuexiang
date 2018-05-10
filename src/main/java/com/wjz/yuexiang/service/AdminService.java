package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.Admin;

/**
 * Created by Jinzi Wu at 20:52 on 2018/5/5.
 */
public interface AdminService {
    Admin getAdmin(String idNumber,String password);
}
