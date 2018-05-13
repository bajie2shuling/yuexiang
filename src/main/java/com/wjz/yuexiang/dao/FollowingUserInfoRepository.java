package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.FollowingUserInfo;
import com.wjz.yuexiang.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jinzi Wu at 17:28 on 2018/5/13.
 */
public interface FollowingUserInfoRepository extends JpaRepository<FollowingUserInfo,Long>{
    FollowingUserInfo findByFollowingIdAndUser(Long followingId,User user);
    void deleteByFollowingIdAndUser(Long followingId,User user);
}
