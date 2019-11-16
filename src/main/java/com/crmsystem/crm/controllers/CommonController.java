package com.crmsystem.crm.controllers;

import com.crmsystem.crm.util.Myfinal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-07 19:52
 * @version:第1版
 * @description:公共的控制器，主要用于处理获取常量类的数据的一些请求，或归属不明显的请求。
 **/
@Controller
@RequestMapping("sys")
public class CommonController {

    //获取年份
    @RequestMapping(value = "/getYear.json")
    @ResponseBody
    public Object getYear(){
        String [] year = Myfinal.YEAR;
        return year;
    }

    //获取月份
    @RequestMapping(value = "/getMonth.json")
    @ResponseBody
    public Object getMonth(){
        String [] month = Myfinal.MONTH;
        return month;
    }

    //跳转到用户管理的方法
    @RequestMapping(value = "/userManage.html")
    public String getUserManage(){
        return "sys/manage/userManage";
    }


}
