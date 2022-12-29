package com.holanswide.blog.controller;

import com.alibaba.fastjson.JSON;
import com.holanswide.blog.pojo.Essay;
import com.holanswide.blog.pojo.Field;
import com.holanswide.blog.pojo.User;
import com.holanswide.blog.service.EssayService;
import com.holanswide.blog.service.UserService;
import com.holanswide.blog.util.DataSet;
import com.holanswide.blog.util.GetNew;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
            } else if(key.equals("auto")) {
                // 首页推荐
                es=essayService.queryEssayAuto(begin,end);
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
        String msg="添加成功";
        int sign=1;
        // check essay
        User user=userService.queryUserByUid(e.getUid());
        if(user==null) {
            msg="找不到该用户"; sign=0;
        } else {
            if(!(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()).equals(e.getUsername())) ){
                msg="用户非法:"+SecurityContextHolder.getContext().getAuthentication().getName();
                sign=0;
            }
            else {
                // pid
                e.setPid(new GetNew().newPid(e.getFid(),essayService.queryCount()));
                // time
                e.setTime(String.valueOf(
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                if(e.getSummary()==null || e.getSummary().equals(""))
                    e.setSummary(e.getBody().replaceAll("[\t\n\r]",""));
                essayService.addEssay(e);
                result.put("data",e.getPid());
            }
        }
        result.put("sign",sign);
        result.put("msg",msg);
        return JSON.toJSONString(result);
    }

    @GetMapping("/field")
    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @ApiOperation(value = "获取分区")
    public @ResponseBody
    String getField(@RequestParam(value = "fid",required = false) String fid, @RequestParam(value = "fname",required = false) String fname) {
        result.clear();
        int sign=1;
        Object data=null;
        if(fid!=null) {
            data=essayService.queryFieldByFid(fid);
            if(data==null) sign=0;
        } else if (fname!=null) {
            data = essayService.queryFieldByFname(fname);
            System.out.println(data);
            if(data==null) sign=0;
        } else {
            data=essayService.queryFieldAll();
        }
        result.put("data",data);
        result.put("msg","查询成功");
        result.put("sign",sign);
        return JSON.toJSONString(result);
    }

    @PutMapping("/field")
    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @ApiOperation(value = "增加分区")
    public @ResponseBody
    String addField(@RequestBody Field f) {
        result.clear();
        String msg="添加成功";
        int sign=1;
        // check if it exists
        if(essayService.queryFieldByFid(f.getFid())!=null) {
            msg="该分区号已存在";
            sign=0;
        } else if(essayService.queryFieldByFname(f.getFname())!=null) {
            msg="该分区名已存在";
            sign=0;
        } else essayService.addField(f);
        result.put("data",essayService.queryFieldAll());
        result.put("msg",msg);
        result.put("sign",sign);
        return JSON.toJSONString(result);
    }
}
