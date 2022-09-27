package com.holanswide.blog.controller;

import com.alibaba.fastjson.JSON;
import com.holanswide.blog.pojo.User;
import com.holanswide.blog.service.UserService;
import com.holanswide.blog.util.GetNew;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    HashMap<String, Object> result = new HashMap<>();

    // add user with username & password
    @PreAuthorize("permitAll()")
    @PutMapping("/user")
    public @ResponseBody
    String addUser(@RequestParam(value = "user", required = true) String userJson) {
        User userToAdd = JSON.parseObject(userJson, User.class);
        // search if its username has existed
        if (userService.queryUserByUsername(userToAdd.getUsername()) != null) {
            result.put("msg", "用户名已存在");
        } else {
            userService.addUser(userToAdd);
            result.put("msg", "添加成功");
            result.put("data", JSON.toJSONString(userService.queryUserByUsername(userToAdd.getUsername())));
        }
        result.put("status", 201);
        return JSON.toJSONString(result);
    }

    // search user by uid or username
//    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @GetMapping("/user")
    public @ResponseBody
    String getUserByParam(HttpServletResponse response, @RequestParam(value = "uid", required = false) String uid, @RequestParam(value = "username", required = false) String username) {
        if (uid != null && !uid.equals("")) {
            result.put("data", userService.queryUserByUid(uid));
            result.put("status", 200);
            String msg = result.get("data") == null ? "未找到该uid对应的用户" : "查询成功";
            result.put("msg", msg);
        } else if (username != null && !username.equals("")) {
            result.put("data", userService.queryUserByUsername(username));
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
        return JSON.toJSONString(
                userService.queryUserByUsername(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                )
        );
    }
}
