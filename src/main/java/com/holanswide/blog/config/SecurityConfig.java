package com.holanswide.blog.config;

import com.holanswide.blog.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //方法级安全验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
    private Encoder encoder;

    //    权限管理
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/hello").hasRole("a0")
        ;
        // 登录页指定；未指定默认 /login
        http.formLogin().loginPage("/").loginProcessingUrl("/dologin").permitAll().defaultSuccessUrl("/1");
        // 关闭csrf防止get post攻击
        http.csrf().disable();
        // 注销
        // 访问 /logout 就能自动注销，注销后会跳转到 logoutSuccessURL
        http.logout().logoutSuccessUrl("/");
        // 记住我功能
        http.rememberMe();
    }
    // 用户认证

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(encoder)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
