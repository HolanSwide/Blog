package com.holanswide.blog.dao;

import com.holanswide.blog.pojo.Essay;
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
    public List<Essay> queryEssayByIndex(@Param("search") String seach, @Param("begin") int begin, @Param("end") int end);
    public void addEssay(Essay essay);
    public void delEssay(@Param("pid") String pid);
}

