package com.holanswide.blog.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@Controller
public class HelloController {
    @PreAuthorize("hasAnyRole('a0','a1','a2')")
    @GetMapping("/1")
    public @ResponseBody
    String testHello() {
        return "Hello,world!";
    }
    @RequestMapping({"/",})
    public String toLogin() {
        return "login";
    }
}
