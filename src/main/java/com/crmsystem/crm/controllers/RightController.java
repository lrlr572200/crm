package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Right;
import com.crmsystem.crm.entity.System;
import com.crmsystem.crm.service.RightService;
import com.crmsystem.crm.service.SystemService;
import com.crmsystem.crm.util.RoleRight;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private RightService rightService;
    @Resource
    private SystemService systemService;



    //获取权限等级分组
    @RequestMapping("/getRightMain.ajax")
    @ResponseBody
    public Object getMain(System system){
        List<System> systemList =systemService.findEquSystem(system);
        return systemList;
    }

    //异步获取权限等级，根据权限内容
    @RequestMapping(value = "/getMain.json")
    @ResponseBody
    public Object doGetMain(@RequestParam(value = "main",required = true) String main){
        Right right = rightService.findRightByMain(main);
        return right;
    }

    //异步根据角色ID查看权限等级
    @RequestMapping(value = "/getGradeByRoleid.ajax")
    @ResponseBody
    public Object doGetGrade(@RequestParam(value = "rolesId",required = true) Integer rolesId){
        int grade = rightService.findGradeByRolesId(rolesId);
        return grade;
    }



}
