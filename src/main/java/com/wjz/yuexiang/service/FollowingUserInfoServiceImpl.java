package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.FollowingUserInfoRepository;
import com.wjz.yuexiang.po.FollowingUserInfo;
import com.wjz.yuexiang.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jinzi Wu at 17:55 on 2018/5/13.
 */
@Service
public class FollowingUserInfoServiceImpl implements FollowingUserInfoService{

    @Autowired
    private FollowingUserInfoRepository followingUserInfoRepository;

    @Transactional
    @Override
    public FollowingUserInfo generateFollowingUserInfo(Long userId, User user) {
        FollowingUserInfo followingUserInfo = new FollowingUserInfo();
        followingUserInfo.setId(userId);
        followingUserInfo.setUser(user);
        return followingUserInfoRepository.save(followingUserInfo);
    }
}
