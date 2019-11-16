package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Roles;
import com.crmsystem.crm.service.RolesService;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-12 12:24
 * @version:第1版
 * @description:角色控制器
 **/
@Controller
@RequestMapping("sys")
public class RolesController {

    @Resource
    private RolesService rolesService;

    //跳转到角色管理
    @RequestMapping(value = "/roleManage.html")
    public String getRoleManage(){
        return "sys/manage/roleManage";
    }

    //获得角色
    @RequestMapping(value = "/getRoles.ajax")
    @ResponseBody
    public Object getRoles(){
        List<Roles> rolesList = rolesService.findRole();
        return rolesList;
    }
}
