package com.holanswide.blog.dao;

import com.holanswide.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@Mapper
@Repository
public interface UserMapper {
    public List<User> queryUserAll();
    public User queryUserByUid(@Param("uid") String uid);
    public User queryUserByUsername(@Param("username") String username);
    public int queryUserCount();
    public void addUser(User user);



}
