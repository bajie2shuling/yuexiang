package com.wjz.yuexiang.vo;

import com.wjz.yuexiang.po.BookReview;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Created by Jinzi Wu at 11:41 on 2018/5/10.
 */
public class BookReviewPost {

    private Long id;

    @NotBlank(message = "书评标题不能为空")
    private String title;

    @NotBlank(message = "书籍名称不能为空")
    private String bookName;

    @NotBlank(message = "作者姓名不能为空")
    private String bookAuthor;

    @NotBlank(message = "首图地址不能为空")
    private String firstPicture;

    @NotBlank(message = "书评内容不能为空")
    private String content;

    @Length(max = 100,message = "书评描述不能超过100个字符")
    @NotBlank(message = "书评描述不能为空")
    private String description;

    private Boolean allowComment;   //允许评论：1代表允许，0代表保存

    @Min(value = 0,message = "状态不能小于0")
    @Max(value = 1,message = "状态不能大于1")
    private Integer status;   //书评状态：3代表审核通过，2代表审核未通过，1代表待审核，0代表待发布


    public BookReviewPost() {
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


    public BookReview convertToBookReview(){
        BookReview bookReview = new BookReview();
        BeanUtils.copyProperties(this,bookReview);
        return bookReview;
    }
}
