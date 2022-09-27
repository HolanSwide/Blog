package com.holanswide.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/9/27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment) {
        Profiles profiles = Profiles.of("dev","test");
        boolean enableSwagger = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("officer00")
                .apiInfo(apiInfo())
                .enable(enableSwagger);
    }
    // 文档内容框架信息
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "blog API",
                "simple quick start",
                "v1.0,",
                "https://github.com/HolanSwide",
                ApiInfo.DEFAULT_CONTACT,
                "MIT",
                "https://mit-license.org/",
                new ArrayList<>()
        );
    }
}
