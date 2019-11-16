package com.crmsystem.crm.controllers;

import com.crmsystem.crm.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-09 16:42
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
@Controller
@RequestMapping("sys")
public class SystemController {

    //跳转到系统信息的方法
    @RequestMapping("/systemManage.html")
    public String getSystemManage(){
        return "sys/manage/systemManage";
    }

}
