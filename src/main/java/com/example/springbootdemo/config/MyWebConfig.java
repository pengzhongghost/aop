package com.example.springbootdemo.config;

import com.example.springbootdemo.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;
    /**
     * TODO 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，即拦击哪些请求
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }

    //处理统一的返回结果的配置
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0,new MappingJackson2HttpMessageConverter());
    }

}
