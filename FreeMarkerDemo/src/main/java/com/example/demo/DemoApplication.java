package com.example.demo;

import com.example.demo.service.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @RequestMapping("hs")
    public String hs() {
        return "OK";
    }

    @Resource
    private MailService mailService;

    @RequestMapping("send")
    public String send() {
        mailService.sendMail();
        return "√√√";
    }



}
