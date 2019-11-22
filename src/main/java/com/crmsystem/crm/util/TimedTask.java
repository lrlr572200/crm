package com.crmsystem.crm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-21 19:43
 * @version:第1版
 * @description:定时任务类
 **/

@Service
public class TimedTask implements SchedulingConfigurer {

    //引入日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //定时任务cron常量
    //表示每年每月1日凌晨1时
    //private static final String DEFAULT_CRON = "0 0 1 1 * ? *";
    private static final String DEFAULT_CRON = "0 * * * * MON-SAT";

    //设置cron变量，使其可以修改
    private String cron = DEFAULT_CRON;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        //不能允许该值被置空
        if(cron==null || "".equals(cron)){
            cron=DEFAULT_CRON;
        }
        logger.info("当前cron将被修改为：【"+cron+"】。");
        this.cron = cron;
    }

    //重写定时任务设置
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                // 定时任务的业务逻辑
                System.out.println("动态修改定时任务cron参数");
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 定时任务触发，可修改定时任务的执行周期
                CronTrigger trigger = new CronTrigger(cron);
                Date nextExecDate = trigger.nextExecutionTime(triggerContext);
                return nextExecDate;
            }
        });
    }



}
