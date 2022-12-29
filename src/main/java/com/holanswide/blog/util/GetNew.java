package com.holanswide.blog.util;

import com.holanswide.blog.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@Component
public class GetNew {
    public String newUid(int count) {
        // produce a new UID
        return String.valueOf(count+10000000);
    }
    public String newPid(String fid, int count) {
        return fid + "-" + String.valueOf(100001+count);
    }
}
