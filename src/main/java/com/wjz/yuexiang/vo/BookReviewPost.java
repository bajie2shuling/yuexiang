package com.wjz.yuexiang.vo;

import com.wjz.yuexiang.po.BookReview;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * Created by Jinzi Wu at 11:41 on 2018/5/10.
 */
public class BookReviewPost {

    @NotBlank(message = "用户名不能为空")
    private String title;

    @NotBlank(message = "用户名不能为空")
    private String bookName;

    @NotBlank(message = "用户名不能为空")
    private String bookAuthor;

    @NotBlank(message = "用户名不能为空")
    private String firstPicture;

    @NotBlank(message = "用户名不能为空")
    private String content;

    @NotBlank(message = "用户名不能为空")
    private String description;

    private Boolean allowComment;   //允许评论：1代表允许，0代表保存

    private Integer status;   //书评状态：3代表审核通过，2代表审核未通过，1代表待审核，0代表待发布


    public BookReviewPost() {
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


    public BookReview convertToBookReview(){
        BookReview bookReview = new BookReview();
        BeanUtils.copyProperties(this,bookReview);
        return bookReview;
    }
}
