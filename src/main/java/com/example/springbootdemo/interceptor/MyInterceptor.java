package com.example.springbootdemo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(handler);
        //请求的方法是否上一个web接口方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //所在类
            Class<?> aClass = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            //判断类上是否加了@ResponseResult注解
            if (aClass.isAnnotationPresent(NotApiResultFormat.class)) {
                //设置次请求返回体，需要包装，向下传递，在responsebodyadvice接口进行判断
                request.setAttribute("response", aClass.getAnnotation(NotApiResultFormat.class));
                //如果类上没有，再判断方法上
            } else if (method.isAnnotationPresent(NotApiResultFormat.class)) {
                //设置次请求返回体，需要包装，向下传递，在responsebodyadvice接口进行判断
                request.setAttribute("response", method.getAnnotation(NotApiResultFormat.class));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
