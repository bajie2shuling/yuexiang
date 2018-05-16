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

    /**
     *有了就删除，还没有就保存，但是在保存之前判断了是不是保存的自己
     */
    @Transactional
    @Override
    public FollowingUserInfo saveOrDelete(Long followingId, User user) {
        if(followingUserInfoRepository.findByFollowingIdAndUser(followingId,user) != null){
            followingUserInfoRepository.deleteByFollowingIdAndUser(followingId,user);
            return null;
        }
        if(followingId == user.getId()){
            throw new RuntimeException("不能关注自己");
        }
        FollowingUserInfo followingUserInfo = new FollowingUserInfo();
        followingUserInfo.setFollowingId(followingId);
        followingUserInfo.setUser(user);
        return followingUserInfoRepository.save(followingUserInfo);
    }
}
