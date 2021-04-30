package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Value( "${date.value}" )
    String dateValue;

    @GetMapping("test")
    public String test() {
        return "打印配置中心的 dateValue 值: "+ dateValue;
    }

    @GetMapping("test2")
    public void test2() {
        log.debug("我是 debug 打印出的日志");
        log.info("我是 info 打印出的日志");
        log.error("我是 error 打印出的日志");
    }

}