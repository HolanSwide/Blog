package com.holanswide.blog.service;

import com.holanswide.blog.dao.FollowMapper;
import com.holanswide.blog.pojo.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/12/24
 */
@Service
public class FollowService implements FollowMapper {
    @Autowired
    FollowMapper followMapper;
    @Override
    public List<Follow> queryByUid(String uid) {
        return followMapper.queryByUid(uid);
    }

    @Override
    public List<Follow> queryByFollowUid(String followUid) {
        return followMapper.queryByFollowUid(followUid);
    }

    @Override
    public void deleteFollow(String uid, String followUid) {
        followMapper.deleteFollow(uid,followUid);
    }

    @Override
    public Follow queryByUidAndFollowUid(String uid, String followUid) {
        return followMapper.queryByUidAndFollowUid(uid, followUid);
    }

    @Override
    public void insertFollow(Follow follow) {
        followMapper.insertFollow(follow);
    }
}
