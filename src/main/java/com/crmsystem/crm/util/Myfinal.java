package com.crmsystem.crm.util;

import org.omg.CORBA.INTERNAL;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-04 20:39
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
public class Myfinal {
    //在职状态
    public static final String ON_JOB="在职";
    public static final String OFF_JOB="离职";
    public static final String IN_JOB="试用期";
    //页大小
    public static final Integer PAGESIZE=3;
    //分页数量
    public static final Integer pageSize = 5;
    //年份
    public static final String[] YEAR={"2015","2016","2017","2018","2019"};
    //月份
    public static final String[] MONTH={"01","02","03","04","05","06",
            "07","08","09","10","11","12"};



    //初始密码的设置
    public static final String PWD="123456";

    //定时任务的状态常量
    public static final String CLOSE="CRON_CLOSE";  //关闭
    public static final String OPEN="CRON_OPEN"; //开启


}
