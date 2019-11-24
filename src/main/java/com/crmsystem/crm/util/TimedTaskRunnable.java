package com.crmsystem.crm.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-22 18:37
 * @version:第1版
 * @description:  部门月报任务线程
 **/
public class TimedTaskRunnable implements Runnable {
    public void run() {
        System.out.println(new Date().toString()+"执行*******部门月报任务");
    }
}
