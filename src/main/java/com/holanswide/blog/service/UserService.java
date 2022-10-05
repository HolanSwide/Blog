package com.holanswide.blog.service;

import com.holanswide.blog.dao.UserMapper;
import com.holanswide.blog.pojo.User;
import com.holanswide.blog.util.Encoder;
import com.holanswide.blog.util.GetNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@Service
public class UserService implements UserMapper {
    @Autowired
    UserMapper userMapper;
    @Autowired
    GetNew getNew;

    @Override
    public List<User> queryUserAll() {
        return userMapper.queryUserAll();
    }

    @Override
    public User queryUserByUid(String uid) {
        return userMapper.queryUserByUid(uid);
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    @Override
    public int queryUserCount() {
        return userMapper.queryUserCount();
    }

    @Override
    public void addUser(User user) {
        // remember to encode the pwd
        user.setPassword(
                Encoder.encode(user.getPassword())
        );
        // produce uid
        user.setUid(
                getNew.newUid(this.queryUserCount())
        );
        userMapper.addUser(user);
    }

    @Override
    public void updUser(User user) {
        userMapper.updUser(user);
    }


}
