package com.holanswide.blog.dao;

import com.holanswide.blog.pojo.Essay;
import com.holanswide.blog.pojo.Field;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/10/3
 */

@Mapper
@Repository
public interface EssayMapper {
    public List<Essay> queryEssayByParam(@Param("key")String key, @Param("value")String value, @Param("begin") int begin, @Param("end") int end);
    public List<Field> queryFieldAll();
    public List<Essay> queryEssayAuto(@Param("begin") int begin, @Param("end") int end);
    public Field queryFieldByFid(@Param("fid") String fid);
    public Field queryFieldByFname(@Param("fname") String fname);
    public List<Essay> queryEssayByIndex(@Param("search") String search, @Param("begin") int begin, @Param("end") int end);
    public int queryCount();
    public void addEssay(Essay essay);
    public void delEssay(@Param("pid") String pid);
    public void addField(Field field);
}

