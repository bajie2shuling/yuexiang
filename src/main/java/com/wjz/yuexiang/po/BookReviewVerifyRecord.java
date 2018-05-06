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

    private BookReview bookReview;

    private Admin admin;
}
