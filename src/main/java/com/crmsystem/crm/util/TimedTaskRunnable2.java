package com.crmsystem.crm.util;

import java.util.Date;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-23 11:31
 * @version:第1版
 * @description: 公司月报任务线程
 **/
public class TimedTaskRunnable2 implements Runnable {
    public void run() {
        System.out.println(new Date().toString()+"执行---------公司月报任务！");
    }
}
