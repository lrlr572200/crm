package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Right;
import com.crmsystem.crm.entity.Roles;
import com.crmsystem.crm.service.RightService;
import com.crmsystem.crm.service.RolesService;
import com.crmsystem.crm.util.RoleRight;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private RightService rightService;

    //跳转到角色管理
    @RequestMapping("/roleManage.html")
    public String getRightManage(){
        return "sys/manage/roleManage";
    }

    //获得角色
    @RequestMapping(value = "/getRoles.ajax")
    @ResponseBody
    public Object getRoles(){
        List<Roles> rolesList = rolesService.findRole();
        return rolesList;
    }

    //异步查看角色及其权限
    @RequestMapping(value = "/getroleRight.ajax")
    @ResponseBody
    public Object doRoleRight(@RequestParam(value = "main",required = false) String main,
                              @RequestParam(value = "pageIndex",required = true,defaultValue = "0") Integer pageIndex,
                              @RequestParam(value = "pageSize",required = true,defaultValue = "10")Integer pageSize){
        Map<Object,Object> roleMap = new HashMap<Object,Object>();
        if ("".equals(main)){
            main=null;
        }
        int total = rolesService.findRoleRightCot(main);
        List<RoleRight> data = rolesService.findRoleAndRight(main,pageIndex,pageSize);
        roleMap.put("total",total);
        roleMap.put("data",data);
        return roleMap;
    }

    //异步添加角色
    @RequestMapping(value = "/addRole.ajax",method = RequestMethod.POST)
    @ResponseBody
    public Object doAddRole(RoleRight roleRight){
        int sign = rolesService.addRole(roleRight);
        return sign;
    }

    //异步验证是否有相同角色名的角色
    @RequestMapping(value = "/roleName.json",method = RequestMethod.POST)
    @ResponseBody
    public Object verifyRoleName(@RequestParam(value = "rolesName",required = true) String rolesName){
        int sign = 0;
        Roles roles = rolesService.findRoleByName(rolesName);
        if (roles!=null){
            sign=1;
        }
        return sign;
    }

    //编辑角色的方法
    @RequestMapping(value = "/updRole.ajax")
    @ResponseBody
    public Object doUpdRole(RoleRight reRt){
        int sign = rolesService.updRole(reRt);
        return sign;
    }

    //异步删除角色的方法
    @RequestMapping(value = "/delRole.ajax")
    @ResponseBody
    public Object doDelRole(@RequestParam(value = "rolesId",required = true) Integer rolesId){
        int sign= rolesService.delRole(rolesId);
        return sign;
    }

}
