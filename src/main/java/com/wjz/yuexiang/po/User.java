package com.wjz.yuexiang.po;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jinzi Wu at 14:14 on 2018/5/2.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String nickName;

    private String email;

    private String password;

    private String avatar;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date createTime;

    @OneToMany(mappedBy = "user")
    private List<BookReview> bookReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Book> books = new ArrayList<>();

    @ManyToMany
    private List<BookForest> forests = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FollowingUserInfo> followingUserInfos = new ArrayList<>();

    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<BookReview> getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(List<BookReview> bookReviews) {
        this.bookReviews = bookReviews;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<BookForest> getForests() {
        return forests;
    }

    public void setForests(List<BookForest> forests) {
        this.forests = forests;
    }

    public List<FollowingUserInfo> getFollowingUserInfos() {
        return followingUserInfos;
    }

    public void setFollowingUserInfos(List<FollowingUserInfo> followingUserInfos) {
        this.followingUserInfos = followingUserInfos;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", bookReviews=" + bookReviews +
                ", books=" + books +
                ", forests=" + forests +
                ", followingUserInfos=" + followingUserInfos +
                '}';
    }
}
