package com.holanswide.blog.pojo;

import org.springframework.stereotype.Repository;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/12/24
 */
@Repository
public class Follow {
    String uid;
    String username;
    String followUid;
    String followUsername;
    String time;

    public Follow() {
    }

    public Follow(String uid, String username, String followUid, String followUsername, String time) {
        this.uid = uid;
        this.username = username;
        this.followUid = followUid;
        this.followUsername = followUsername;
        this.time = time;
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

    public String getFollowUid() {
        return followUid;
    }

    public void setFollowUid(String followUid) {
        this.followUid = followUid;
    }

    public String getFollowUsername() {
        return followUsername;
    }

    public void setFollowUsername(String followUsername) {
        this.followUsername = followUsername;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
