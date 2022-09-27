package com.holanswide.blog.pojo;

import org.springframework.stereotype.Repository;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@Repository
public class User {
    String uid;
    String username;
    String password;
    String auth;

    public User() {
    }

    public User(String uid, String username, String password, String auth) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.auth = auth;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
