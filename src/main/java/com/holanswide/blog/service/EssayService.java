package com.holanswide.blog.service;

import com.holanswide.blog.dao.EssayMapper;
import com.holanswide.blog.pojo.Essay;
import com.holanswide.blog.pojo.Field;
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
    public List<Field> queryFieldAll() {
        return essayMapper.queryFieldAll();
    }

    @Override
    public List<Essay> queryEssayAuto(int begin, int end) {
        return essayMapper.queryEssayAuto(begin, end);
    }

    @Override
    public Field queryFieldByFid(String fid) {
        return essayMapper.queryFieldByFid(fid);
    }

    @Override
    public Field queryFieldByFname(String fname) {
        return essayMapper.queryFieldByFname(fname);
    }

    @Override
    public List<Essay> queryEssayByIndex(String search, int begin, int end) {
        return essayMapper.queryEssayByIndex(search,begin,end);
    }

    @Override
    public int queryCount() {
        return essayMapper.queryCount();
    }

    @Override
    public void addEssay(Essay essay) {
        essayMapper.addEssay(essay);
    }

    @Override
    public void delEssay(String pid) {
        essayMapper.delEssay(pid);
    }

    @Override
    public void addField(Field field) {
        essayMapper.addField(field);
    }
}
