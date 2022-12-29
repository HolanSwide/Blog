package com.holanswide.blog.dao;

import com.holanswide.blog.pojo.User;
import com.holanswide.blog.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/28
 */
@Mapper
@Repository
public interface UserInfoMapper {
    public List<UserInfo> queryUserInfoAll();
    public UserInfo queryUserInfoByUid(@Param("uid") String uid);
    public UserInfo queryUserInfoByParam(@Param("param") String param,@Param("valueStr") String valueStr);

    public void insertUserInfo(UserInfo userInfo);

    public void delUserInfo(@Param("uid") String uid);
}
