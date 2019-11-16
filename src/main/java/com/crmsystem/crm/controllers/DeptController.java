package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Dept;
import com.crmsystem.crm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-11 12:27
 * @version:第1版
 * @description: 部门管理控制器
 **/
@Controller
@RequestMapping("sys")
public class DeptController {

    @Resource
    private DeptService deptService;

    //获取下级部门的方法
    @RequestMapping(value = "/getSuborDept.ajax",method = RequestMethod.GET)
    @ResponseBody
    public Object doSuborDept(@RequestParam(value = "deptId",required = false) Integer deptId){
        if("".equals(deptId) || deptId==null ){
            deptId=null;
        }
        List<Dept> depts = deptService.findDeptByParentid(deptId);
        return depts;
    }

    //跳转到部门管理
    @RequestMapping(value = "/deptManage.html")
    public String getDeptManage(){
        return "sys/manage/deptManage";
    }

    //获取部门的方法
    @RequestMapping(value = "/getDept.ajax")
    @ResponseBody
    public Object getDept(){
        List<Dept> depts = deptService.findDept();
        return depts;
    }

}
