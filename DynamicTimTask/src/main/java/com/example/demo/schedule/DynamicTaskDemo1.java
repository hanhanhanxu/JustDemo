package com.example.demo.schedule;

import com.example.demo.mapper.DynamicTimTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: HanXu
 * on 2021/4/29
 * Class description: 具体任务1
 */
@Slf4j
@Component
public class DynamicTaskDemo1 extends DynamicConfigureScheduling {

    @Resource
    private DynamicTimTaskMapper dynamicTimTaskMapper;

    @Override
    protected void detailTask() {
        log.info("{}执行任务1111111", Thread.currentThread().getName());
    }

    @Override
    protected String getDynamicCron() {
        return dynamicTimTaskMapper.selectById(1).getCron();
    }

    @Override
    protected String getCron() {
        return "0 0/2 * * * ?";
    }
}
