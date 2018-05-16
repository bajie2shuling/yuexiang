package com.wjz.yuexiang.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jinzi Wu at 11:58 on 2018/5/16.
 */
@Entity
public class BookForestApplyVerifyRecord {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private Integer result; //处理结果：2代表审核通过，1代表审核未通过

    private String bookForestName;

    private String bookForestDescription;

    @ManyToOne
    private BookForestApply bookForestApply;

    @ManyToOne
    private Admin admin;

    public BookForestApplyVerifyRecord() {
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

    public BookForestApply getBookForestApply() {
        return bookForestApply;
    }

    public void setBookForestApply(BookForestApply bookForestApply) {
        this.bookForestApply = bookForestApply;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "BookForestApplyVerifyRecord{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", result=" + result +
                ", bookForestName='" + bookForestName + '\'' +
                ", bookForestDescription='" + bookForestDescription + '\'' +
                ", bookForestApply=" + bookForestApply +
                ", admin=" + admin +
                '}';
    }
}
