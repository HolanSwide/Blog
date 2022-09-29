package com.holanswide.blog.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/28
 */
@RestController
@RequestMapping("/error")
public class ErrorController {
    HashMap<String,Object> hashMap = new HashMap<>();
    @RequestMapping("/login")
    public @ResponseBody
    String loginFailure(HttpServletRequest request) {
        hashMap.put("sign",0);
        hashMap.put("msg", "登录失败，请检查账号或密码是否正确");
        return JSON.toJSONString(hashMap);
    }
}
