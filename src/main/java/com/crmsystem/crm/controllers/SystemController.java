package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.System;
import com.crmsystem.crm.service.SystemService;
import com.crmsystem.crm.util.Myfinal;
import com.crmsystem.crm.util.TimedTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private SystemService systemService;
    @Resource
    private TimedTask task;

    //跳转到系统信息的方法
    @RequestMapping("/systemManage.html")
    public String getSystemManage(){
        return "sys/manage/systemManage";
    }

    @RequestMapping("/rili.html")
    public String rili()
    {
        return "sys/rili";
    }

    //获取定时任务列表的方法
    @RequestMapping("/getTask.ajax")
    @ResponseBody
    public Object getTask(@RequestParam(value = "taskKey",required = true,defaultValue = "CRON") String taskKey,
                          @RequestParam(value = "pageIndex",required = true,defaultValue = "0") Integer pageIndex,
                          @RequestParam(value = "pageSize",required = true,defaultValue = "5") Integer pageSize){
        Map<Object,Object> taskMap = new HashMap<Object, Object>();
        System sys = new System();
        sys.setDeclare(taskKey);
        List<System> systems =systemService.findTimedTask(sys,pageIndex,pageSize);
        int total = systemService.findCot(sys);
        taskMap.put("total",total);
        taskMap.put("data",systems);
        return taskMap;
    }

    //修改定时任务
    @RequestMapping("/updTask.ajax")
    @ResponseBody
    public Object updTask(System system){
        int sign = 0;
        String declare =null;
        if (system.getDeclare()!=null && !"".equals(system.getDeclare())){
            declare = system.getDeclare();
        }
        System theSystem = systemService.findTheSystem(system.getId());
        boolean flg = false;
        if (Myfinal.OPEN.equals(declare)){  //调用开启线程的方法
            if (system.getId()==3){ //部门月报方法
                flg=task.startDeptTask(theSystem.getValue());
            }else if (system.getId()==4){  //公司月报方法
                flg=task.startComTask(theSystem.getValue());
            }
        }else if (Myfinal.CLOSE.equals(declare)){ //调用关闭线程的方法
            if (system.getId()==3){ //部门月报方法
                flg=task.stopDeptTask();
            }else if (system.getId()==4){  //公司月报方法
                flg=task.stopComTask();
            }
        }
        if (flg){
            sign=systemService.updSystem(system);
        }
        return sign;
    }

    //获取cron
    @RequestMapping(value = "/getCron.json")
    @ResponseBody
    public Object getCron(System system){
        List<System> systems = systemService.findAllSystem(system);
        return  systems;
    }

    //获取某一变量
    @RequestMapping(value = "/getsys.json")
    @ResponseBody
    public Object getSys(System system){
        System theSystem = systemService.findTheSystem(system.getId());
        return theSystem;
    }

    //修改cron
    @RequestMapping(value = "/updCron.ajax")
    @ResponseBody
    public Object updCron(System system){
        int sign = 0;
        boolean flg = false;
        int id = system.getId();
        if (id==3){ //修改部门月报的cron
            flg=task.changeDeptTask(system.getValue());
        }else if (id==4){ //修改公司月报的cron
            flg=task.changeComTask(system.getValue());
        }
        if (flg){
            sign=systemService.updSystem(system);
        }
        return sign;
    }

}
