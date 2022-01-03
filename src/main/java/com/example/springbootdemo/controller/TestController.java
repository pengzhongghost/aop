package com.example.springbootdemo.controller;

import com.example.springbootdemo.interceptor.CatchException;
import com.example.springbootdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//非我设定的返回格式
//@NotApiResultFormat
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    //捕获方法上的异常注解
    //@CatchException
    public String test(@RequestParam("name") String name) {
        //int i=1/0;
        return testService.test();
    }

}
