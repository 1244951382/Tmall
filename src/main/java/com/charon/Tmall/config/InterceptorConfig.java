package com.charon.Tmall.config;

import com.charon.Tmall.config.Interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName InterceptorConfig
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/4 23:12
 * @Version 1.0
 **/

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求，通过判断token是否合法来决定是否需要登录
        registry.addInterceptor(jwtInterceptor())
                //拦截路径
                .addPathPatterns("/**")
                //放行路径
                .excludePathPatterns(
                        "/user/login",
                        "/user/register",
                        "/**/export",
                        "/**/import",
                        "/file/**",
                        "/front/login",
                        "/front/register"
                );
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
