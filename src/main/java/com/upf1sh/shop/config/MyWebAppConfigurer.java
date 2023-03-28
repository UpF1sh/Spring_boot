package com.upf1sh.shop.config;

import com.upf1sh.shop.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/data/upload/**").addResourceLocations("file:" + "/data/upload/");
        registry.addResourceHandler("/data/icon/**").addResourceLocations("file:" + "/data/icon/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" +System.getProperty("user.dir")+ "/upload/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .addPathPatterns("/brand/**")
                .addPathPatterns("/category/**");//拦截user下的请求
    }
}


