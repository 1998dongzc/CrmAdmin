package com.dzc.admin.common.config;

import com.dzc.admin.common.interceptor.AdminTokenIntercreptor;
import com.dzc.admin.common.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: 董政辰
 * @date: 2021/3/4 19:48
 * @description: 配置拦截器拦截路径
 * @email：532587041@qq.com
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    TokenInterceptor tokenInterceptor;

    @Autowired
    AdminTokenIntercreptor adminTokenIntercreptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
        registry.addInterceptor(adminTokenIntercreptor).addPathPatterns("/**");
    }
}
