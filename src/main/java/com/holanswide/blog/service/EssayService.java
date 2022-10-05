package com.holanswide.blog.service;

import com.holanswide.blog.dao.EssayMapper;
import com.holanswide.blog.pojo.Essay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/10/3
 */
@Service
public class EssayService implements EssayMapper{
    @Autowired
    EssayMapper essayMapper;

    @Override
    public List<Essay> queryEssayByParam(String key, String value, int begin, int end) {
        return essayMapper.queryEssayByParam(key, value, begin, end);
    }

    @Override
    public List<Essay> queryEssayByIndex(String seach, int begin, int end) {
        return essayMapper.queryEssayByIndex(seach,begin,end);
    }

    @Override
    public void addEssay(Essay essay) {
        essayMapper.addEssay(essay);
    }

    @Override
    public void delEssay(String pid) {
        essayMapper.delEssay(pid);
    }
}
