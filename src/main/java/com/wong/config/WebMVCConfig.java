package com.wong.config;

import com.wong.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;
    /**
     * 配置一个拦截器，只放行UserController部分，其他部分全部需要token认证
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(loginInterceptor)
               .addPathPatterns("/**")
               .excludePathPatterns("/user/**");
    }
}
