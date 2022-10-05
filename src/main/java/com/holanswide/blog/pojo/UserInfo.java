package com.holanswide.blog.pojo;

import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author ：holan
 * @description : 用户信息类
 * @date ：2022/9/28
 */
@Repository

public class UserInfo {
    String uid;
    String email;
    String phone;
    String birth;
    String address;
    String sex;
    @Column(name = "sign",columnDefinition = "tinytext")
    String sign;

    public UserInfo() {
    }

    public UserInfo(String uid, String email, String phone, String birth, String address, String sex, String sign) {
        this.uid = uid;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
        this.sex = sex;
        this.sign = sign;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
