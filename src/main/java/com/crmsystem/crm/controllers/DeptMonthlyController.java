package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Deptmonthly;
import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.service.DeptmonthlyService;
import com.crmsystem.crm.service.EmpService;
import com.crmsystem.crm.service.OrderService;
import com.crmsystem.crm.util.DeptMonthlyUtil;
import com.crmsystem.crm.util.GetFirstAndLast;
import com.crmsystem.crm.util.Myfinal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-12 15:07
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
@Controller
@RequestMapping("sys")
public class DeptMonthlyController {
    @Resource
    DeptmonthlyService deptmonthlyService;
    @Resource
    EmpService empService;
    @Resource
    OrderService orderService;
    //异步显示数据
    @RequestMapping(value = "/deptMonthlys.html",method = RequestMethod.POST)
    @ResponseBody
    public List deptmonthly(Deptmonthly monthly,HttpSession session)
    {
        List list=new ArrayList();
        Emp emp = (Emp)session.getAttribute("session");
        monthly.setDeptId(emp.getDeptId());
        Deptmonthly deptmonthly=deptmonthlyService.findDeptMonthlyByYM(monthly);
        list.add(deptmonthly);
        //查看该部门所有销售代表
        List<String> nameList = empService.findEmpByDeptAndRolesId(emp.getDeptId(), emp.getRolesId(), Myfinal.OFF_JOB);
        //创建工具类对象
        DeptMonthlyUtil deptMonthlyUtil=new DeptMonthlyUtil();
        deptMonthlyUtil.setSpace("");
        //获取当前月份
        Integer month =Integer.parseInt(monthly.getMonth());
        //获取当月第一天
        String fday = GetFirstAndLast.getFirstDayOfMonth(month);
        //获取当月最后一天
        String lday = GetFirstAndLast.getLastDayOfMonth(month);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fdate = null;
        Date ldate=null;
        try {
            fdate=format.parse(fday);
            ldate=format.parse(lday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        deptMonthlyUtil.setStarTime(fdate);
        deptMonthlyUtil.setEndTime(ldate);
        List<String> names=new ArrayList<String>();
        List<Integer> moneys=new ArrayList<Integer>();
        //遍历查看各销售代表销售总金额
        for(String name:nameList)
        {
            deptMonthlyUtil.setEmpName(name);
            names.add(name);
            Double sumMoney = orderService.findSumMoneyAndPactCode(deptMonthlyUtil);
            Integer newMoney ;
            if(sumMoney==null)
            {
                newMoney=0;
            }else {
                String a=sumMoney.toString();
                a=a.substring(0,a.indexOf("."));
                newMoney=Integer.parseInt(a);
            }
            moneys.add(newMoney);
        }
        list.add(names);
        list.add(moneys);
        return list;
    }
    //显示部门月报
    @RequestMapping("/deptMonthly.html")
    public String deptMonthly(HttpSession session)
    {
        Emp emp = (Emp)session.getAttribute("session");
        if(emp.getRolesId()!=3)
        {
            return "redirect:/sys/err.html";
        }
        return "sys/deptMonthly/deptMonthly";
    }


}
