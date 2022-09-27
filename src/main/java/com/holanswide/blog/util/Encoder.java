package com.holanswide.blog.util;

import com.holanswide.blog.pojo.User;
import com.holanswide.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@Component
public class Encoder implements UserDetailsService {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // get user-info by username
        User user = userService.queryUserByUsername(username);
        if (user==null) { throw new UsernameNotFoundException("Username Not Found"); }
        // get role from user
        String role = user.getAuth();
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+role));
        // shouldn't encode password, cz pwd in database has been encoded
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorityList
        );

    }
}
