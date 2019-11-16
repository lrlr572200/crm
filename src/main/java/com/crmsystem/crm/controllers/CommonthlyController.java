package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Commonthly;
import com.crmsystem.crm.entity.Dept;
import com.crmsystem.crm.entity.Deptmonthly;
import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.service.CommonthlyService;
import com.crmsystem.crm.service.DeptService;
import com.crmsystem.crm.service.OrderService;
import com.crmsystem.crm.util.CommonthlyUtil;
import com.crmsystem.crm.util.DeptMonthlyUtil;
import com.crmsystem.crm.util.GetFirstAndLast;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-14 16:43
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
@Controller
@RequestMapping("sys")
public class CommonthlyController {
    @Resource
    CommonthlyService commonthlyService;
    @Resource
    DeptService deptService;
    @Resource
    OrderService orderService;
    //异步显示数据
    @RequestMapping(value = "/commonthlys.html",method = RequestMethod.POST)
    @ResponseBody
    public List commonthlys(Commonthly com)
    {
        List list=new ArrayList();
        /*查看公司的当月月报*/
        Commonthly commonthly=commonthlyService.findCommonthlyByYM(com);
        list.add(commonthly);
        //查看公司所有销售部门
        List<Dept> deptList = deptService.findDeptByParentid(3);
        //创建工具类对象
        CommonthlyUtil commonthlyUtil=new CommonthlyUtil();
        commonthlyUtil.setSpace("");
        //获取当前月份
        Integer month =Integer.parseInt(com.getMonth());
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
        commonthlyUtil.setStarTime(fdate);
        commonthlyUtil.setEndTime(ldate);

        List<String> names=new ArrayList<String>();
        List<Integer> moneys=new ArrayList<Integer>();
        //遍历查看各销售部门销售总金额
        for(Dept dept:deptList)
        {
            commonthlyUtil.setDeptName(dept.getDeptName());
            names.add(dept.getDeptName());
            Double sumMoney = orderService.findSumMoneyAndPactCodeByDeptName(commonthlyUtil);
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


    //显示公司月报
    @RequestMapping("/commonthly.html")
    public  String commonthly(HttpSession session)
    {
        Integer grade = (Integer) session.getAttribute("grade");
        if(grade!=10)
        {
            return "redirect:/sys/err.html";
        }
        return "sys/commonthly/commonthly";
    }
}
