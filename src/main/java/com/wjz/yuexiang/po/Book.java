package com.wjz.yuexiang.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jinzi Wu at 0:11 on 2018/5/10.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String author;

    private String remark;      //备注版本和出版社信息

    private String reviewTitle;

    private Boolean status;     //是否已经交易完成，0就是未完成，1是完成

    private Boolean shareWay;  //什么方式共享,0就是借，1就是卖

    private String contactInfo;  //联系方式

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String review;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private BookForest bookForest;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getShareWay() {
        return shareWay;
    }

    public void setShareWay(Boolean shareWay) {
        this.shareWay = shareWay;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookForest getBookForest() {
        return bookForest;
    }

    public void setBookForest(BookForest bookForest) {
        this.bookForest = bookForest;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", remark='" + remark + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", status=" + status +
                ", shareWay=" + shareWay +
                ", contactInfo='" + contactInfo + '\'' +
                ", review='" + review + '\'' +
                ", createTime=" + createTime +
                ", user=" + user +
                ", bookForest=" + bookForest +
                '}';
    }
}
