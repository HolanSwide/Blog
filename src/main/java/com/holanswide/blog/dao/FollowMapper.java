package com.holanswide.blog.dao;

import com.holanswide.blog.pojo.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/12/24
 */
@Mapper
@Repository
public interface FollowMapper {
    List<Follow> queryByUid(@Param("uid") String uid);
    List<Follow> queryByFollowUid(@Param("followUid") String followUid);
    void deleteFollow(@Param("uid") String uid, @Param("followUid") String followUid);
    Follow queryByUidAndFollowUid( @Param("uid") String uid, @Param("followUid") String followUid);
    void insertFollow(Follow follow);
}
