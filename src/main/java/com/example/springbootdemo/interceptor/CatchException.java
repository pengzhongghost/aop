package com.example.springbootdemo.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//捕获方法上的异常注解
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CatchException {

}
