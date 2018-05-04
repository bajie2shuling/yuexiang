package com.wjz.yuexiang.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jinzi Wu at 16:16 on 2018/5/3.
 */
@Entity
@Table(name = "t_book_review")
public class BookReview {

    //private static final String FIRSTPICTURE_REGEXP = null;
    //private static final String BOOKNAME_REGEXP = null;

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    //@Pattern(regexp = BOOKNAME_REGEXP , message = "手机号格式不正确")
    private String bookName;

    private String bookAuthor;

    //@Pattern(regexp = FIRSTPICTURE_REGEXP , message = "手机号格式不正确")
    private String firstPicture;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;

    private String description;

    private Boolean allowComment;   //允许评论：1代表允许，0代表保存


    private Integer status;   //书评状态：2代表审核通过，1代表审核未通过，0代表待发布

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    private Integer views;      //浏览次数

    @ManyToOne
    private User user;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", views=" + views +
                ", user=" + user +
                '}';
    }
}
