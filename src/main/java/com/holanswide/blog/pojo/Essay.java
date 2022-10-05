package com.holanswide.blog.pojo;

import org.springframework.stereotype.Repository;

import javax.persistence.Column;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/10/3
 */
@Repository
public class Essay {
    String pid;
    String fid;
    String uid;
    String username;
    String time;
    String cover;
    String title;
    String summary;
    @Column(name = "body",columnDefinition = "text")
    String body;

    public Essay() {
    }

    public Essay(String pid, String fid, String uid, String username, String time, String cover, String title, String summary, String body) {
        this.pid = pid;
        this.fid = fid;
        this.uid = uid;
        this.username = username;
        this.time = time;
        this.cover = cover;
        this.title = title;
        this.summary = summary;
        this.body = body;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
