package com.crmsystem.crm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-21 19:43
 * @version:第1版
 * @description:定时任务类
 **/

@Component
public class TimedTask  {

    /**
     * 在ScheduledFuture中有一个cancel可以停止定时任务。
     * ThreadPoolTaskScheduler：线程池任务调度类，能够开启线程池进行任务调度
     * ThreadPoolTaskScheduler.schedule()方法会创建一个定时计划ScheduledFuture，在这个方法需要添加两个参数，Runnable（线程接口类） 和CronTrigger（定时任务触发器）
     */


    //引入日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //线程池任务调度类
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    //有可以停止定时任务的cancel方法的ScheduledFuture类
    private ScheduledFuture<?> deptFuture;
    private ScheduledFuture<?> comFuture;

    //定时任务cron常量,防止cron为空
    /*private static final String DEPT_CRON = "0 0 1 1 1-12 ?";
    private static final String COM_CRON = "0 0 1 2 1-12 ?";*/


    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    //开启部门月报的方法
    public boolean startDeptTask(String cron){
        /*if (cron==null || "".equals(cron)){
            cron=DEPT_CRON;
        }*/
        boolean flg = true;
        deptFuture = threadPoolTaskScheduler.schedule(new TimedTaskRunnable(),
                new CronTrigger(cron));
        if (deptFuture != null){
            logger.info(new Date().toString()+"开启部门月报定时任务！");
        }else {
            flg = false;
            logger.error(new Date().toString()+"部门月报定时任务开启失败！");
        }
        return flg;
    }

    //关闭部门月报的方法
    @SuppressWarnings(value="all")
    public boolean stopDeptTask(){
        boolean flg = true;
        if (deptFuture != null) {
            deptFuture.cancel(true);
        }else {
            flg=false;
            logger.error(new Date().toString()+"关闭null值的部门月报任务，关闭失败！");
        }
        if (deptFuture != null){
            logger.info(new Date().toString()+"关闭部门月报定时任务！");
        }else {
            flg = false;
            logger.error(new Date().toString()+"关闭部门月报定时任务时失败！");
        }
        return flg;
    }

    //修改部门月报cron的方法
    public boolean changeDeptTask(String cron){
        /*if (cron==null || "".equals(cron)){
            cron=DEPT_CRON;
        }*/
        boolean flg = true;
        stopDeptTask();// 先停止，再开启
        deptFuture = threadPoolTaskScheduler.schedule(new TimedTaskRunnable(),
                new CronTrigger(cron));
        if (deptFuture != null){
            logger.info(new Date().toString()+"重启部门月报定时任务！cron修改为"+cron);
        }else {
            flg = false;
            logger.error(new Date().toString()+"部门月报定时任务修改失败！cron为"+cron);
        }
        return flg;
    }

    //开启公司月报的方法
    public boolean startComTask(String cron){
        /*if (cron==null || "".equals(cron)){
            cron=COM_CRON;
        }*/
        boolean flg = true;
        comFuture = threadPoolTaskScheduler.schedule(new TimedTaskRunnable2(),
                new CronTrigger(cron));
        if (comFuture != null){
            logger.info(new Date().toString()+"开启公司月报定时任务！");
        }else {
            flg = false;
            logger.error(new Date().toString()+"公司月报定时任务开启失败！");
        }
        return flg;
    }

    //关闭公司月报的方法
    @SuppressWarnings(value="all")
    public boolean stopComTask(){
        boolean flg = true;
        if (comFuture != null) {
            comFuture.cancel(true);
        }else {
            flg=false;
            logger.error(new Date().toString()+"关闭null值的部门月报任务，关闭失败！");
        }
        if (comFuture != null){
            logger.info(new Date().toString()+"关闭部门月报定时任务！");
        }else {
            flg = false;
            logger.error(new Date().toString()+"关闭部门月报定时任务时失败！");
        }
        return flg;
    }

    //修改公司月报cron的方法
    public boolean changeComTask(String cron){
       /* if (cron==null || "".equals(cron)){
            cron=COM_CRON;
        }*/
        boolean flg = true;
        stopComTask();// 先停止，再开启
        comFuture = threadPoolTaskScheduler.schedule(new TimedTaskRunnable2(),
                new CronTrigger(cron));
        if (comFuture != null){
            logger.info(new Date().toString()+"重启公司月报定时任务！cron修改为"+cron);
        }else {
            flg = false;
            logger.error(new Date().toString()+"公司月报定时任务修改失败！cron为"+cron);
        }
        return flg;
    }






    /*//引入日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //线程池任务调度类
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    //ScheduledFuture中有一个cancel可以停止定时任务
    private ScheduledFuture<?> future;
    *//*private Map<String,ScheduledFuture<?>> futureMap = new HashMap<>();*//*


    //定时任务cron常量
    private static final String DEFAULT_CRON = "0 * * * * MON-SAT";
    *//**
     * 每年每月1日凌晨1时0分0秒  "0 0 1 1 1-12 ? *"
     * 每年每月2日凌晨2时0分0秒  "0 0 1 2 1-12 ? *"
     * 每年每月每日每时每分钟    "0 * * * * MON-SAT"
     *//*


    //设置cron变量，使其可以修改
    private String cron = DEFAULT_CRON;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        if (cron==null || "".equals(cron)){
            cron=DEFAULT_CRON;
        }
        this.cron = cron;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    //启动定时任务
    public boolean startCron(Runnable task) {
        boolean flg = true;
        future = threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));

        if (future != null){
            logger.info(new Date().toString()+"开启定时任务！");
        }else {
            flg = false;
            logger.error(new Date().toString()+"定时任务开启失败！");
        }
        return flg;
    }

    //关闭定时任务
    public boolean stopCron() {
        boolean flg = true;
        if (future != null) {
            future.cancel(true);
        }else {
            flg=false;
            logger.error(new Date().toString()+"关闭null值任务，关闭失败！");
        }
        if (future != null){
            logger.info(new Date().toString()+"关闭定时任务！");
        }else {
            flg = false;
            logger.error(new Date().toString()+"关闭定时任务时失败！");
        }
        return flg;
    }

    //修改定时任务的cron
    public boolean changeCron() {
        boolean flg = true;
        stopCron();// 先停止，再开启
        future = threadPoolTaskScheduler.schedule(new TimedTaskRunnable(), new CronTrigger(cron));
        if (future != null){
            logger.info(new Date().toString()+"重启定时任务！cron修改为"+cron);
        }else {
            flg = false;
            logger.error(new Date().toString()+"定时任务修改失败！cron为"+cron);
        }
        return flg;
    }*/


/*

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
*/



}
