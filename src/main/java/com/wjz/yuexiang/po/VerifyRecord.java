package com.wjz.yuexiang.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jinzi Wu at 20:38 on 2018/5/16.
 */
@Entity
public class VerifyRecord {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private Long userId;        //审核内容所属的用户ID

    private String nickName;        //审核内容所属的用户名

    private String verifyContentBriefInfo;  //审核的内容的简要信息

    private Integer result; //处理结果：1代表审核通过，0代表审核未通过

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private BookReview bookReview;

    @ManyToOne
    private BookForestCreateApply bookForestCreateApply;

    @ManyToOne
    private Book book;

    public VerifyRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVerifyContentBriefInfo() {
        return verifyContentBriefInfo;
    }

    public void setVerifyContentBriefInfo(String verifyContentBriefInfo) {
        this.verifyContentBriefInfo = verifyContentBriefInfo;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public BookReview getBookReview() {
        return bookReview;
    }

    public void setBookReview(BookReview bookReview) {
        this.bookReview = bookReview;
    }

    public BookForestCreateApply getBookForestCreateApply() {
        return bookForestCreateApply;
    }

    public void setBookForestCreateApply(BookForestCreateApply bookForestCreateApply) {
        this.bookForestCreateApply = bookForestCreateApply;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "VerifyRecord{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", verifyContentBriefInfo='" + verifyContentBriefInfo + '\'' +
                ", result=" + result +
                ", admin=" + admin +
                ", bookReview=" + bookReview +
                ", bookForestCreateApply=" + bookForestCreateApply +
                ", book=" + book +
                '}';
    }
}
