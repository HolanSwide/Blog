package com.holanswide.blog.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@Controller
public class HelloController {
    HashMap<String,Object> hashMap = new HashMap<>();

    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @GetMapping("/1")
    public @ResponseBody
    String testHello() {
        return "Hello,world!";
    }
    @RequestMapping({"/blog"})
    public String toMain() {
        return "redirect:http://localhost/app/index.html";
    }

    @PreAuthorize("permitAll()")
    @RequestMapping({"/tologin","/"})
    public String toLogin() {return "login";}

    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @RequestMapping("/main")
    public @ResponseBody
    String toMainPage() {
        hashMap.put("status",200);
        hashMap.put("msg","登录成功");
        hashMap.put("sign",1);
        // 存入指向主页url
        hashMap.put("url","http://localhost/blog");
        return JSON.toJSONString(hashMap);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/reg")
    public String toReg() {
        return "reg";
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/repass")
    public String toRepass() {
        return "repass";
    }
}
