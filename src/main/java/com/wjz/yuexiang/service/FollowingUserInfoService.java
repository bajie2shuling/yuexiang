package com.wjz.yuexiang.service;

import com.wjz.yuexiang.po.FollowingUserInfo;
import com.wjz.yuexiang.po.User;

/**
 * Created by Jinzi Wu at 17:26 on 2018/5/13.
 */
public interface FollowingUserInfoService {
    FollowingUserInfo generateFollowingUserInfo(Long userId,User user);
}
