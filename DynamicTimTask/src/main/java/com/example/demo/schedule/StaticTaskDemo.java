package com.example.demo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: HanXu
 * on 2021/4/29
 * Class description: 静态任务
 */
@Slf4j
@Component
public class StaticTaskDemo {

    @Scheduled(cron = "0 0/3 * * * ?")
    public void execute() {
        log.info("{}执行任务静态任务", Thread.currentThread().getName());
    }
}
