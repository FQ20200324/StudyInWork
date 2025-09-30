package com.fq.config;

import com.fq.config.interceptor.TraceIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    TraceIdInterceptor traceIdInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //addMapping 添加可跨域的请求地址
        registry.addMapping("/**")
                //设置跨域 域名权限 规定由某一个指定的域名+端口能访问跨域项目
                .allowedOrigins("*")
                //是否开启cookie跨域
                .allowCredentials(false)
                //规定能够跨域访问的方法类型
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")

                .exposedHeaders("traceId")
                //预检请求存活时间 在此期间不再次发送预检请求
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        //配置链路追踪traceId拦截器
        registry.addInterceptor(traceIdInterceptor).addPathPatterns("/**");
    }

}
