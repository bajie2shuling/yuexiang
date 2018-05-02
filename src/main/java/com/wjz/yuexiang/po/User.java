package com.wjz.yuexiang.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jinzi Wu at 14:14 on 2018/5/2.
 */
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String password;
}
