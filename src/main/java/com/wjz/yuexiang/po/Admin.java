package com.wjz.yuexiang.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jinzi Wu at 19:59 on 2018/5/5.
 */
@Entity
@Table(name = "t_admin")
public class Admin {

    @Id
    @GeneratedValue
    private Long id;
    private String idNumber;
    private String password;
    private String name;
    private String email;
    private String Phone;
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Admin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", idNumber='" + idNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
