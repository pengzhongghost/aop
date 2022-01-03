package com.example.springbootdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AspectJAdvice {

    @Pointcut("execution(* com.example.springbootdemo.controller..*.*(..))")
    public void pointcut() {

    }

    @Pointcut("@annotation(com.example.springbootdemo.interceptor.CatchException)")
    public void catchException(){

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //http://localhost:8080/test
        log.info(request.getRequestURL().toString());
        //GET
        log.info(request.getMethod());
        //0:0:0:0:0:0:0:1
        log.info(request.getRemoteAddr());
        //class com.example.springbootdemo.controller.TestController_com.example.springbootdemo.controller.TestController
        log.info(joinPoint.getSignature().getDeclaringType() + "_" + joinPoint.getSignature().getDeclaringTypeName());
        //获取连接点方法运行时的入参列表 [zhangsan]
        log.info(Arrays.toString(joinPoint.getArgs()));
    }

    //returning 定义形参名
    @AfterReturning(returning = "obj", pointcut = "pointcut()")
    public void afterReturn(Object obj) {
        log.warn(obj + "");
    }

    //切点抛出异常后执行
    @AfterThrowing("pointcut()")
    public void afterThrow() {

    }

    //方法后置执行
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {

    }

    //捕获方法上的异常注解
    @Around("catchException()")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature =(MethodSignature)joinPoint.getSignature();
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            log.warn(e + "");
        }
        return "你好";
    }

}
