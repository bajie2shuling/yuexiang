package com.wjz.yuexiang.vo;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;

/**
 * Created by Jinzi Wu at 20:14 on 2018/5/5.
 */
public class AdminSignIn {

    private final static String REGEX_IDNUMBER = "(^\\d{18}$)|(^\\d{15}$)";

    @Pattern(regexp = REGEX_IDNUMBER,message = "身份证号无效")
    private String idNumber;

    @Length(min = 6,message = "密码长度至少需要6位")
    private String password;

    public AdminSignIn() {
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
}
