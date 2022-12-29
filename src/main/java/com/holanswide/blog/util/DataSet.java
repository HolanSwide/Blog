package com.holanswide.blog.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/28
 */
@Component
public class DataSet {
    public static final HashSet<String> USERINFO_KEY_SET = new HashSet<>(
            Arrays.asList( "uid","email","phone" )
    );
    public static final HashSet<String> USER_KEY_SET = new HashSet<>(
            Arrays.asList( "uid","username","password","auth" )
    );
    public static final HashSet<String> ESSAY_KEY_SET = new HashSet<>(
            Arrays.asList("auto","pid","fid","uid","username","username","time","body","cover","title","summary")
    );
}
