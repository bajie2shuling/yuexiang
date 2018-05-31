package com.wjz.yuexiang.vo;

/**
 * Created by Jinzi Wu at 22:17 on 2018/5/31.
 */
public class BookPost {

    private Long bookReviewId;

    private Long bookForestId;

    private String remark;      //备注版本和出版社信息


    private Boolean shareWay;  //什么方式共享,0就是借，1就是卖

    private String contactInfo;  //联系方式

    public BookPost() {
    }

    public Long getBookReviewId() {
        return bookReviewId;
    }

    public void setBookReviewId(Long bookReviewId) {
        this.bookReviewId = bookReviewId;
    }

    public Long getBookForestId() {
        return bookForestId;
    }

    public void setBookForestId(Long bookForestId) {
        this.bookForestId = bookForestId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getShareWay() {
        return shareWay;
    }

    public void setShareWay(Boolean shareWay) {
        this.shareWay = shareWay;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
