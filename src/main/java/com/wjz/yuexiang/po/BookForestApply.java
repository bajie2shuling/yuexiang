package com.wjz.yuexiang.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jinzi Wu at 12:22 on 2018/5/16.
 */
@Entity
public class BookForestApply {
    @Id
    @GeneratedValue
    private Long id;

    private String bookForestName;

    private String bookForestDescription;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;


    private Integer status;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "bookForestApply")
    private List<BookForestApplyVerifyRecord> bookForestApplyVerifyRecords = new ArrayList<>();

    public BookForestApply() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookForestName() {
        return bookForestName;
    }

    public void setBookForestName(String bookForestName) {
        this.bookForestName = bookForestName;
    }

    public String getBookForestDescription() {
        return bookForestDescription;
    }

    public void setBookForestDescription(String bookForestDescription) {
        this.bookForestDescription = bookForestDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BookForestApplyVerifyRecord> getBookForestApplyVerifyRecords() {
        return bookForestApplyVerifyRecords;
    }

    public void setBookForestApplyVerifyRecords(List<BookForestApplyVerifyRecord> bookForestApplyVerifyRecords) {
        this.bookForestApplyVerifyRecords = bookForestApplyVerifyRecords;
    }

    @Override
    public String toString() {
        return "BookForestApply{" +
                "id=" + id +
                ", bookForestName='" + bookForestName + '\'' +
                ", bookForestDescription='" + bookForestDescription + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", user=" + user +
                ", bookForestApplyVerifyRecords=" + bookForestApplyVerifyRecords +
                '}';
    }
}
