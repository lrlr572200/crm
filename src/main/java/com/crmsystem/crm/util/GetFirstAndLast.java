package com.crmsystem.crm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-13 11:36
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
public class GetFirstAndLast {
    //获得第一天
    public static String getFirstDayOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime())+" 00:00:00";
        return firstDayOfMonth;
    }
    //获得最后一天
    public static String getLastDayOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay=0;
        //2月的平年瑞年天数
        if(month==2) {
            lastDay = cal.getLeastMaximum(Calendar.DAY_OF_MONTH);
        }else {
            lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime())+" 23:59:59";
        return lastDayOfMonth;
    }





}
