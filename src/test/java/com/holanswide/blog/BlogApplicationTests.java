package com.holanswide.blog;

import com.holanswide.blog.service.UserService;
import com.holanswide.blog.util.Encoder;
import com.holanswide.blog.util.GetNew;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    GetNew getNew;
    @Test
    void contextLoads() {
        System.out.println(getNew.newUid(userService.queryUserCount()));
    }

}
