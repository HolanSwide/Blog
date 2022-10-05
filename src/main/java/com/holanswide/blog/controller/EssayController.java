package com.holanswide.blog.controller;

import com.alibaba.fastjson.JSON;
import com.holanswide.blog.pojo.Essay;
import com.holanswide.blog.service.EssayService;
import com.holanswide.blog.service.UserService;
import com.holanswide.blog.util.DataSet;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/10/3
 */
@Controller
public class EssayController {
    @Autowired
    EssayService essayService;
    @Autowired
    UserService userService;
    HashMap<String, Object> result = new HashMap<>();

    @PostMapping("/essay")
    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @ApiOperation(value = "获取文章",notes = "接收4个参数：key,value,begin,end")
    public @ResponseBody
    String getEssay(@RequestBody HashMap<String,String> request) {
        result.clear();
        String key=request.get("key");
        String value=request.get("value");
        int begin=Integer.parseInt(request.get("begin"));
        int end=Integer.parseInt(request.get("end"));
        int sign=1;
        String msg="查询成功";
        // check key&value
        if (!DataSet.ESSAY_KEY_SET.contains(key) || value==null) {
            sign=0;
            msg="参数非法";
        } else {
            List<Essay> es =null;
            if(key.equals("body") || key.equals("title") || key.equals("summary")) {
                es = essayService.queryEssayByIndex(value,begin,end);
            } else
                es = essayService.queryEssayByParam(key,value,begin,end);
            if(es.isEmpty()) {
                msg="找不到内容";
                sign=0;
            }
            result.put("data",es);
        }
        // write res
        result.put("sign",sign);
        result.put("msg",msg);
        return JSON.toJSONString(result);
    }

    @PutMapping(value = "/essay",consumes = "application/json")
    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @ApiOperation(value = "添加文章")
    public @ResponseBody
    String addEssay(@RequestBody Essay e) {
        result.clear();
        // check essay

    }

}
