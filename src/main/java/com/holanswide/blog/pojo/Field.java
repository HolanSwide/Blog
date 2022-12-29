package com.holanswide.blog.pojo;

import org.springframework.stereotype.Repository;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/10/6
 */
@Repository
public class Field {
    String fid;
    String fname;

    public Field() {
    }

    public Field(String fid, String fname) {
        this.fid = fid;
        this.fname = fname;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
