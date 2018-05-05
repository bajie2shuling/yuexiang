package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.AdminRepository;
import com.wjz.yuexiang.po.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jinzi Wu at 20:59 on 2018/5/5.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin signInCheckAdmin(String idNumber, String password) {
        return adminRepository.findByIdNumberAndPassword(idNumber,password);
    }
}
