package com.charon.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName WebMvcConfigurer
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/1/24 20:18
 * @Version 1.0
 **/
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
    /**
     * 当前跨域请求最大有效时长。这里默认1天
     */
    private static final long MAX_AGE = 24 * 60 * 60;

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(MAX_AGE);
    }
}
