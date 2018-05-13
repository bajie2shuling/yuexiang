package com.wjz.yuexiang.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Jinzi Wu at 12:09 on 2018/5/13.
 */
@Entity
public class FollowingUserInfo {

    @Id
    @GeneratedValue
    private Long id;

    private Long followingId;

    @ManyToOne
    private User user;

    public FollowingUserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FollowingUserInfo{" +
                "id=" + id +
                ", followingId=" + followingId +
                ", user=" + user +
                '}';
    }
}
