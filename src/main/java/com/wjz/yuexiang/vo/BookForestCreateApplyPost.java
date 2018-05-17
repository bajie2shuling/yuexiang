package com.wjz.yuexiang.vo;

import com.wjz.yuexiang.po.BookForestCreateApply;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * Created by Jinzi Wu at 20:42 on 2018/5/17.
 */
public class BookForestCreateApplyPost {

    private Long id;

    @NotBlank(message = "书林名称不能为空")
    private String bookForestName;

    @NotBlank(message = "书林描述不能为空")
    private String bookForestDescription;

    public BookForestCreateApplyPost() {
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

    public BookForestCreateApply convertToBookForestCreateApply(){
        BookForestCreateApply bookForestCreateApply = new BookForestCreateApply();
        BeanUtils.copyProperties(this,bookForestCreateApply);
        return bookForestCreateApply;
    }
}
