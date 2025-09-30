package com.fq.config;

import com.fq.config.interceptor.TraceIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    TraceIdInterceptor traceIdInterceptor;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        //确保父类默认注册的拦截器（如Spring内置的HandlerInterceptor）被保留
        WebMvcConfigurer.super.addInterceptors(registry);
        //配置链路追踪traceId拦截器
        registry.addInterceptor(traceIdInterceptor).addPathPatterns("/**");
    }

}
