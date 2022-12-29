package com.holanswide.blog.service;

import com.holanswide.blog.dao.UserInfoMapper;
import com.holanswide.blog.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/28
 */
@Service
public class UserInfoService implements UserInfoMapper {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public List<UserInfo> queryUserInfoAll() {
        return userInfoMapper.queryUserInfoAll();
    }

    @Override
    public UserInfo queryUserInfoByUid(String uid) {
        return userInfoMapper.queryUserInfoByUid(uid);
    }

    @Override
    public UserInfo queryUserInfoByParam(String param, String valueStr) {
        return userInfoMapper.queryUserInfoByParam(param,valueStr);
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        userInfoMapper.insertUserInfo(userInfo);
    }

    @Override
    public void delUserInfo(String uid) {
        userInfoMapper.delUserInfo(uid);
    }
}
