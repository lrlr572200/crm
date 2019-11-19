package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.*;
import com.crmsystem.crm.service.CommonthlyService;
import com.crmsystem.crm.service.DeptmonthlyService;
import com.crmsystem.crm.service.OrderService;
import com.crmsystem.crm.service.UserService;
import com.crmsystem.crm.util.CmSum;
import com.crmsystem.crm.util.Myfinal;
import com.crmsystem.crm.util.PageSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.System;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-10 18:28
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
@Controller
@RequestMapping("sys")
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    UserService userService;
    @Resource
    DeptmonthlyService deptmonthlyService;
    @Resource
    CommonthlyService commonthlyService;
    @RequestMapping("/delOrder.html")
    public String delOrder(Integer orderId,Model model)
    {
        Order order = orderService.findOrderById(orderId);
        if(!order.getPactCode().equals("") && order.getPactCode()!=null)
        {
            return "sys/noRight";
        }
        int i = orderService.delOrder(orderId);
        if(i>0)
        {
            return "redirect:/sys/order.html";
        }else{
            model.addAttribute("message","删除错误");
            return "404";
        }
    }
    //******************************************************
    //处理修改合同编号和状态
    @RequestMapping(value = "/updateOrder.html",method = RequestMethod.POST)
    public String updateOrder(Integer orderId,String states,String pactCode)
    {
        if(pactCode==null || pactCode.equals(""))
        {
            pactCode="";
        }
        int i = orderService.updateSPbyOrderId(orderId,states,pactCode);

        if(i>0)
        {
            if(pactCode!=null && pactCode!="")
            {
                Order order = orderService.findOrderById(orderId);
                Integer userId=order.getUserId();
                CmSum CAM = orderService.findCountAndMoney(userId, "");
                Integer C=CAM.getTimes();
                Double M=CAM.getAmount();
                User user=new User();
                user.setUserId(userId);
                //修改客户状态
                //判断信誉等级
                if(C<4)
                {
                    //C
                    user.setCreditGrade("C");
                    userService.updateUserCreditGradeById(user);
                }else if(C<7)
                {
                    //B
                    user.setCreditGrade("B");
                    userService.updateUserCreditGradeById(user);
                }else {
                    //A
                    user.setCreditGrade("A");
                    userService.updateUserCreditGradeById(user);
                    if(M>500000)
                    {
                        //S
                        user.setCreditGrade("S");
                        userService.updateUserCreditGradeById(user);
                    }
                }
                //判断客户类型
                if(M<100000)
                {
                    //普通
                    user.setUserType("普通客户");
                    userService.updateUserUserTypeById(user);
                }else if(M>500000)
                {
                    //VIP
                    user.setUserType("VIP客户");
                    userService.updateUserUserTypeById(user);
                }else {
                    //大客户
                    user.setUserType("大客户");
                    userService.updateUserUserTypeById(user);
                }
                //修改部门月报
                SimpleDateFormat year=new SimpleDateFormat("yyyy");
                SimpleDateFormat month=new SimpleDateFormat("MM");
                Date date=new Date();
                String yyyy=year.format(date);
                String MM=month.format(date);

                Deptmonthly monthly=new Deptmonthly();
                monthly.setYear(yyyy);
                monthly.setMonth(MM);
                monthly.setDeptId(order.getDeptId());
                Deptmonthly deptMonthly = deptmonthlyService.findDeptMonthlyByYM(monthly);
                //如果为空，计算同比环比,创建
                if(deptMonthly==null)
                {
                    double sumMoney = order.getSumMoney();
                    //计算同比
                    Integer newyy=Integer.parseInt(yyyy)-1;
                    String newyyyy=newyy.toString();//上一年
                    monthly.setMonth(MM);
                    monthly.setYear(newyyyy);
                    //查询上一年的当月部门月报
                    monthly.setDeptId(order.getDeptId());
                    Deptmonthly deptmonthly2= deptmonthlyService.findDeptMonthlyByYM(monthly);
                    Double sum1=order.getSumMoney();
                    //*****************解决deptmonthly2为空
                    String basis;
                    if(deptmonthly2==null)
                    {
                        basis=100+"%";
                    }else {
                        Double sum2 = deptmonthly2.getSumMoney();
                        Double b = ((sum1-sum2)/sum2)*100;
                        basis=b+"%";
                    }
                    //计算环比
                    Integer newmm=Integer.parseInt(MM)-1;
                    if(newmm==0)
                    {
                        newmm=12;
                        yyyy=newyyyy;
                    }
                    String newMM = newmm.toString();//上个月
                    monthly.setYear(yyyy);
                    monthly.setMonth(newMM);
                    //查询上个月的月报
                    monthly.setDeptId(order.getDeptId());
                    Deptmonthly deptMonthly3=deptmonthlyService.findDeptMonthlyByYM(monthly);
                    String ratio;
                    if(deptMonthly3==null)
                    {
                        ratio=100+"%";
                    }
                    else{
                        Double sum3= deptMonthly3.getSumMoney();
                        Double r=((sum1-sum3)/sum3)*100;
                        ratio=r+"%";
                    }
                    String YYYY=year.format(date);
                    monthly.setYear(YYYY);
                    monthly.setMonth(MM);
                    monthly.setSumMoney(order.getSumMoney());
                    monthly.setDeptId(order.getDeptId());
                    monthly.setBasis(basis);
                    monthly.setRatio(ratio);
                    deptmonthlyService.addDeptMonthly(monthly);
                }else {
                    //如果不为空
                    Double sum=deptMonthly.getSumMoney()+order.getSumMoney();
                    //计算同比
                    Integer newyy=Integer.parseInt(yyyy)-1;
                    String newyyyy=newyy.toString();//获取上一年
                    monthly.setYear(newyyyy);
                    monthly.setMonth(MM);
                    monthly.setDeptId(order.getDeptId());
                    Deptmonthly deptmonthly2=deptmonthlyService.findDeptMonthlyByYM(monthly);
                    String basis;
                    if(deptmonthly2==null)
                    {
                        basis=100+"%";
                    }else {
                        Double sum2=deptmonthly2.getSumMoney();
                        Double b=((sum-sum2)/sum2)*100;
                        basis=b+"%";
                    }
                    //计算环比
                    Integer newmm=Integer.parseInt(MM)-1;
                    if(newmm==0)
                    {
                        newmm=12;
                        yyyy=newyyyy;
                    }
                    String newMM=newmm.toString();
                    monthly.setMonth(newMM);
                    monthly.setYear(yyyy);
                    monthly.setDeptId(order.getDeptId());
                    Deptmonthly deptmonthly3=deptmonthlyService.findDeptMonthlyByYM(monthly);
                    String ratio;
                    if(deptmonthly3==null)
                    {
                        ratio=100+"%";
                    }else{
                        Double sum3=deptmonthly3.getSumMoney();
                        Double r=((sum-sum3)/sum3)*100;
                        ratio=r+"%";
                    }
                    //执行修改
                    String YYYY=year.format(date);
                    monthly.setYear(YYYY);
                    monthly.setMonth(MM);
                    monthly.setSumMoney(sum);
                    monthly.setDeptId(order.getDeptId());
                    monthly.setBasis(basis);
                    monthly.setRatio(ratio);
                    monthly.setId(deptMonthly.getId());
                    deptmonthlyService.updateDeptMonthly(monthly);
                }//部门月报表修改完毕

                //修改公司月报
                String yyyy2=year.format(date);
                String MM2=month.format(date);
                Commonthly monthly2=new Commonthly();
                monthly2.setYear(yyyy2);
                monthly2.setMonth(MM2);
                Commonthly commonthly = commonthlyService.findCommonthlyByYM(monthly2);
                //如果为空 创建月报
                if(commonthly==null)
                {
                    double sumMoney = order.getSumMoney();
                    //计算同比
                    Integer newyy=Integer.parseInt(yyyy2)-1;
                    String newyyyy=newyy.toString();//上一年
                    monthly2.setMonth(MM2);
                    monthly2.setYear(newyyyy);
                    //查询上一年的公司月报
                    Commonthly commonthly2= commonthlyService.findCommonthlyByYM(monthly2);
                    Double sum1=order.getSumMoney();
                    //*****************解决commonthly2为空
                    String basis;
                    if(commonthly2==null)
                    {
                        basis=100+"%";
                    }else {
                        Double sum2 = commonthly2.getSumMoney();
                        Double b = ((sum1-sum2)/sum2)*100;
                        basis=b+"%";
                    }
                    //计算环比
                    Integer newmm=Integer.parseInt(MM2)-1;
                    if(newmm==0)
                    {
                        newmm=12;
                        yyyy2=newyyyy;
                    }
                    String newMM = newmm.toString();//上个月
                    monthly2.setYear(yyyy2);
                    monthly2.setMonth(newMM);
                    //查询上个月的公司月报
                    Commonthly commonthly3=commonthlyService.findCommonthlyByYM(monthly2);
                    String ratio;
                    if(commonthly3==null)
                    {
                        ratio=100+"%";
                    }
                    else{
                        Double sum3= commonthly3.getSumMoney();
                        Double r=((sum1-sum3)/sum3)*100;
                        ratio=r+"%";
                    }
                    String YYYY2=year.format(date);
                    monthly2.setYear(YYYY2);
                    monthly2.setMonth(MM2);
                    monthly2.setSumMoney(order.getSumMoney());
                    monthly2.setBasis(basis);
                    monthly2.setRatio(ratio);
                    commonthlyService.addCommonthly(monthly2);
                }else {
                    //如果不为空
                    Double sum=commonthly.getSumMoney()+order.getSumMoney();
                    //计算同比
                    Integer newyy=Integer.parseInt(yyyy2)-1;
                    String newyyyy=newyy.toString();//获取上一年
                    monthly2.setYear(newyyyy);
                    monthly2.setMonth(MM2);
                    Commonthly commonthly3=commonthlyService.findCommonthlyByYM(monthly2);
                    String basis;
                    if(commonthly3==null)
                    {
                        basis=100+"%";
                    }else {
                        Double sum2=commonthly3.getSumMoney();
                        Double b=((sum-sum2)/sum2)*100;
                        basis=b+"%";
                    }
                    //计算环比
                    Integer newmm=Integer.parseInt(MM2)-1;
                    if(newmm==0)
                    {
                        newmm=12;
                        yyyy2=newyyyy;
                    }
                    String newMM=newmm.toString();
                    monthly2.setMonth(newMM);
                    monthly2.setYear(yyyy2);
                    Commonthly commonthly4=commonthlyService.findCommonthlyByYM(monthly2);
                    String ratio;
                    if(commonthly4==null)
                    {
                        ratio=100+"%";
                    }else{
                        Double sum3=commonthly4.getSumMoney();
                        Double r=((sum-sum3)/sum3)*100;
                        ratio=r+"%";
                    }
                    //执行修改
                    String YYYY2=year.format(date);
                    monthly2.setYear(YYYY2);
                    monthly2.setMonth(MM2);
                    monthly2.setSumMoney(sum);
                    monthly2.setBasis(basis);
                    monthly2.setRatio(ratio);
                    monthly2.setId(commonthly.getId());
                    commonthlyService.updateCommonthly(monthly2);
                }//公司月报表修改完毕

            }
            return "redirect:/sys/order.html";
        }else{
            return "sys/order/updateOrder";
        }

    }
    //******************************************************************
    //显示修改界面
    @RequestMapping("/updateOrder.html")
    public String updateOrder(Integer orderId,Model model,HttpSession session)
    {
        Emp emp = (Emp)session.getAttribute("session");
        if(emp.getRolesId()!=2 && emp.getRolesId()!=3)
        {
            return "sys/noRight";
        }
        Order order = orderService.findOrderById(orderId);
        model.addAttribute("order",order);
        return "sys/order/updateOrder";
    }




    //显示添加界面
    @RequestMapping("/addOrder.html")
    public String addOrder()
    {
        return "sys/order/addOrder";
    }
    //处理订单添加
    @RequestMapping(value = "/addOrders.html",method = RequestMethod.POST)
    public String addOrder(Order order,HttpSession session)
    {
        Emp emp = (Emp)session.getAttribute("session");
        Date data=new Date();
        order.setAddTime(data);
        String code=emp.getEmpCode();
        System.out.println(code);
        order.setAddCode(code);
        order.setStates("新创建");
        order.setPactCode("");
        order.setRemark("");
        int rel=orderService.addOrder(order);
        if(rel>0)
        {
            User user=new User();
            user.setStates("已成交");
            user.setUserId(order.getUserId());
            userService.updateUserStatesById(user);
            return "redirect:/sys/order.html";
        }else{
            return "sys/order/addOrder";
        }

    }
    //显示订单界面
    @RequestMapping("/order.html")
    public  String order(HttpSession session)
    {
        Emp emp = (Emp)session.getAttribute("session");
        Integer r=emp.getRolesId();
        if(r==2 || r==3 || r==4)
        {
            return "sys/order/order";
        }
        return "sys/noRight";
    }
    //异步过滤分页显示订单
    @RequestMapping(value = "/orders.html",method = RequestMethod.POST)
    @ResponseBody
    public PageSupport<Order> showOrder(Model model, HttpSession session,
        Order order, @RequestParam(required = false,defaultValue = "1")Integer pageIndex)
    {
        PageSupport<Order> pageSupport=new PageSupport<Order>();
        Emp emp = (Emp)session.getAttribute("session");
        order.setRolesId(emp.getRolesId());
        order.setEmpCode(emp.getEmpCode());
        order.setDeptId(emp.getDeptId());
        pageIndex=(pageIndex-1)* Myfinal.PAGESIZE;
        List<Order> orderList = orderService.findOrderPage(order, pageIndex, Myfinal.PAGESIZE);
        pageSupport.setDataList(orderList);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        pageSupport.setPageIndex(pageIndex);
        int totalCount=orderService.findOrderCount(order);
        pageSupport.setTotalCount(totalCount);
        model.addAttribute("pageSupport",pageSupport);
        return pageSupport;
    }

}
