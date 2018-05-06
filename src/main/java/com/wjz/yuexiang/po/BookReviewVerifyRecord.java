package com.wjz.yuexiang.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jinzi Wu at 18:50 on 2018/5/6.
 */
@Entity
@Table(name = "t_verify_record")
public class BookReviewVerifyRecord {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private Integer result; //处理结果：3代表审核通过，2代表审核未通过

    @ManyToOne
    private BookReview bookReview;

    @ManyToOne
    private Admin admin;

    public BookReviewVerifyRecord() {
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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public BookReview getBookReview() {
        return bookReview;
    }

    public void setBookReview(BookReview bookReview) {
        this.bookReview = bookReview;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "BookReviewVerifyRecord{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", result=" + result +
                ", bookReview=" + bookReview +
                ", admin=" + admin +
                '}';
    }
}
