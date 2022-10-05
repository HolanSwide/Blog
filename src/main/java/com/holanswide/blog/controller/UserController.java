package com.holanswide.blog.controller;

import com.alibaba.fastjson.JSON;
import com.holanswide.blog.pojo.User;
import com.holanswide.blog.pojo.UserInfo;
import com.holanswide.blog.service.UserInfoService;
import com.holanswide.blog.service.UserService;
import com.holanswide.blog.util.DataSet;
import com.holanswide.blog.util.Encoder;
import com.holanswide.blog.util.GetNew;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    GetNew getNew;
    @Autowired
    UserInfoService userInfoService;
    HashMap<String, Object> result = new HashMap<>();

    // add user-info
    @PreAuthorize("permitAll()")
    @PutMapping(value = "/info", consumes = "application/json")
    @ApiOperation(value = "添加用户信息", notes = "传入的数据请确保uid正确")
    public @ResponseBody
    String addUserInfo(@RequestBody UserInfo info) {
        result.clear();
        String msg = "添加成功";
        int sign = 1;
        // check if the user exists
        if (userService.queryUserByUid(info.getUid()) == null) {
            sign = 0;
            msg = "该用户不存在！";
        } else {
            // check if the info exists
            for (String str : DataSet.USERINFO_KEY_SET)
                if (userInfoService.queryUserInfoByParam(str, info.getUid()) != null) {
                    msg = "该" + str + "已存在!";
                    sign = 0;
                    break;
                }
            // insert
            if (sign == 1) {
                userInfoService.insertUserInfo(info);
            }
        }
        result.put("msg", msg);
        result.put("sign", sign);
        return JSON.toJSONString(result);
    }

    // search user-info by uid or username or email or phone
    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @PostMapping(value = "/info", consumes = "application/json")
    public @ResponseBody
    String getUserInfo(@RequestBody HashMap<String, Object> request) {
        result.clear();
        String key = (String) request.get("key");
        String value = (String) request.get("value");
        String msg = "查询成功";
        int sign = 1;

        if (!DataSet.USERINFO_KEY_SET.contains(key)) {
            msg = "查询参数非法";
            sign = 0;
        } else {
            UserInfo userInfo = userInfoService.queryUserInfoByParam(key, value);
            if (userInfo == null) {
                sign = 0;
                msg = "没有找到";
                result.put("data", "null");
            } else
                result.put("data", JSON.toJSONString(userInfo));
        }
        result.put("msg", msg);
        result.put("sign", sign);
        return JSON.toJSONString(result);
    }

    // add user with username & password
    @PreAuthorize("permitAll()")
    @PutMapping("/user")
    public @ResponseBody
    String addUser(@RequestBody User userToAdd) {
        result.clear();
        int sign=1;
        // search if its username has existed
        if (userService.queryUserByUsername(userToAdd.getUsername()) != null) {
            result.put("msg", "用户名已存在");
            sign=0;
        } else {
            // 用户权限设置为最低
            userToAdd.setAuth("a2");
            userService.addUser(userToAdd);
            result.put("msg", "添加成功");
            result.put("data", userService.queryUserByUsername(userToAdd.getUsername()).getUid());
        }
        result.put("status", 201);
        result.put("sign",sign);
        return JSON.toJSONString(result);
    }

    // search user by uid or username
    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @GetMapping("/user")
    public @ResponseBody
    String getUserByParam(HttpServletResponse response, @RequestParam(value = "uid", required = false) String uid, @RequestParam(value = "username", required = false) String username) {
        result.clear();
        if (uid != null && !uid.equals("")) {
            result.put("data", userService.queryUserByUid(uid));
            result.put("status", 200);
            String msg = result.get("data") == null ? "未找到该uid对应的用户" : "查询成功";
            result.put("msg", msg);
        } else if (username != null && !username.equals("")) {
            User user = userService.queryUserByUsername(username);
            // 密码保护
            user.setPassword("[PROTECTED]");
            result.put("data", user);
            result.put("status", 200);
            String msg = result.get("data") == null ? "未找到该名称对应的用户" : "查询成功";
            result.put("msg", msg);
        } else {
            result.put("status", 400);
            result.put("msg", "收到参数为空");
        }
        response.setStatus((int) result.get("status"));
        return JSON.toJSONString(result);
    }

    // return 'me'
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    public @ResponseBody
    String getMe() {
        result.clear();
        User user = userService.queryUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        user.setPassword("[PROTECTED]");
        return JSON.toJSONString(
                user
        );
    }

    // change pwd
    @ApiOperation(value = "更改密码",notes = "根据phone")
    @PreAuthorize("permitAll()")
    @PatchMapping(value = "/pwd",consumes = "application/json")
    public @ResponseBody
    String updPwd(@RequestBody HashMap<String,String> request) {
        result.clear();
        String msg="修改成功！";
        User user=null;
        int sign=1;
        String phone = request.get("phone");
        String pwd = request.get("password ");
        // find user
        UserInfo info = userInfoService.queryUserInfoByParam("phone",phone);
        if(info==null) {
            sign=0;
            msg="找不到该用户，请检查参数";
        } else {
            user=userService.queryUserByUid(info.getUid());
        }
        // check uid
        if (user==null) {
            msg="找不到该用户，请检查uid";
            sign=0;
        }
        else if (!userService.queryUserByUid(user.getUid()).getUsername().equals(user.getUsername())) {
            msg="uid与username不匹配！";
            sign=0;
        }
        // encoder pwd
        if(sign==1) {
            user.setPassword(
                    Encoder.encode(pwd)
            );
            userService.updUser(user);
        }
        // return msg only
        result.put("msg",msg);
        result.put("sign",sign);
        return JSON.toJSONString(result);
    }
}
