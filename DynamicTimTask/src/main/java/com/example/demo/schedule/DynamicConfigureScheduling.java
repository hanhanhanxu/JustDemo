package com.example.demo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: HanXu
 * on 2021/4/28
 * Class description: 动态任务抽象类
 */
@Slf4j
@Configuration
@EnableScheduling
public abstract class DynamicConfigureScheduling implements SchedulingConfigurer {


    /**
     * 动态配置cron
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskScheduler());
        scheduledTaskRegistrar.addTriggerTask(
                // 定时任务
                () -> {
                    detailTask();
                },
                // 触发器：在当前任务执行时，获取下一次任务的cron；所以修改cron后，会在下下次生效。
                triggerContext -> {
                    // 从数据库中获取，在页面动态修改
                    String dynamicCron = getDynamicCron();
                    if (StringUtils.isEmpty(dynamicCron)) {
                        dynamicCron = getCron();
                    }
                    return new CronTrigger(dynamicCron).nextExecutionTime(triggerContext);
                }
        );
    }

    /**
     * 设置TaskScheduler用于注册计划任务
     *
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    public Executor taskScheduler() {
        //创建线程池
        return Executors.newScheduledThreadPool(5);
    }

    /**
     * 具体任务
     * 子类实现具体逻辑
     */
    protected abstract void detailTask();

    /**
     * 动态获取cron
     * @return
     */
    protected abstract String getDynamicCron();

    /**
     * 默认周期
     * 返回cron表达式
     * @return
     */
    protected abstract String getCron();

}
