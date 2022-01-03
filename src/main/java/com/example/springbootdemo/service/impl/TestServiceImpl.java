package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.interceptor.CatchException;
import com.example.springbootdemo.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        test01();
        test02();
        return "ok";
    }

    @ExceptionHandler
    public void test01() {
        int i = 1 / 0;
        System.out.println("test01");
    }

    public void test02() {
        System.out.println("test02");
    }

}
