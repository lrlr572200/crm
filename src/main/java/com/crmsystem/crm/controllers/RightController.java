package com.crmsystem.crm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-12 12:29
 * @version:第1版
 * @description: 权限管理的控制器
 **/
@Controller
@RequestMapping("sys")
public class RightController {

    @RequestMapping("/rightManage.html")
    public String getRightManage(){
        return "sys/manage/rightManage";
    }

}
