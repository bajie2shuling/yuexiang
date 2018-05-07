package com.wjz.yuexiang.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jinzi Wu at 16:16 on 2018/5/3.
 */
@Entity
@Table(name = "t_book_review")
public class BookReview {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String bookName;

    private String bookAuthor;

    private String firstPicture;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;

    private String description;

    private Boolean allowComment;   //允许评论：1代表允许，0代表保存


    private Integer status;   //书评状态：3代表审核通过，2代表审核未通过，1代表待审核，0代表待发布

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime;

    private Integer views;      //浏览次数

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "bookReview")
    private List<BookReviewVerifyRecord> bookReviewVerifyRecords = new ArrayList<>();

    public BookReview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public List<BookReviewVerifyRecord> getBookReviewVerifyRecords() {
        return bookReviewVerifyRecords;
    }

    public void setBookReviewVerifyRecords(List<BookReviewVerifyRecord> bookReviewVerifyRecords) {
        this.bookReviewVerifyRecords = bookReviewVerifyRecords;
    }

    @Override
    public String toString() {
        return "BookReview{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", allowComment=" + allowComment +
                ", status=" + status +
                ", publishTime=" + publishTime +
                ", views=" + views +
                ", user=" + user +
                ", bookReviewVerifyRecords=" + bookReviewVerifyRecords +
                '}';
    }
}
