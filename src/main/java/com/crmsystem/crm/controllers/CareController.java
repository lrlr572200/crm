package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Care;
import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.User;
import com.crmsystem.crm.service.CareService;
import com.crmsystem.crm.service.UserService;
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
import javax.xml.ws.spi.http.HttpContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("sys")
public class CareController {
    @Resource
    CareService careService;
    @Resource
    UserService userService;
    //异步获取未过期的关怀计划数量
    @RequestMapping("/findCareByNow.html")
    @ResponseBody
    public Integer findCareByNow(HttpSession session)
    {
        Emp emp = (Emp)session.getAttribute("session");
        Date date=new Date();
        int minus;
        int count=0;
        List<Care> careList = careService.findCareByNow(emp.getEmpCode());
        for (Care care:careList)
        {
            minus=(int) (care.getCareTime().getTime()-date.getTime())/86400000;
            if(minus<2)
            {
                count++;
            }
        }
        return count;
    }

    //处理修改关怀计划
    @RequestMapping("/updateCare.html")
    public String updateCare(Care care,Model model)
    {
        int rel = careService.updateCareById(care);
        if(rel>0)
        {
            return "redirect:/sys/care.html?userId="+care.getUserId();
        }else
        {
            model.addAttribute("message","修改错误");
            return "sys/err";
        }

    }
    //异步根据ID获取关怀计划
    @RequestMapping("/findCareById.html")
    @ResponseBody
    public Care findCareById(Care care)
    {
        Care car = careService.findCareById(care);
        return car;
    }

    //处理删除
    @RequestMapping("/delCare.html")
    @ResponseBody
    public String delCare(Care care)
    {
        int rel = careService.delCare(care);
        if(rel>0)
        {
            return "1";
        }else {
            return "0";
        }
    }

    //处理添加界面
    @RequestMapping(value = "/addCares.html",method = RequestMethod.POST)
    public String addCare(Care care)
    {
        int rel = careService.addCare(care);
        if(rel>0)
        {
            return "redirect:/sys/care.html?userId="+care.getUserId();
        }else{
            return "sys/care/addCare";
        }
    }

    //显示添加界面
    @RequestMapping("/addCare.html")
    public String addCare(){
        return "sys/care/addCare";
    }

    //异步显示客户关怀信息
    @RequestMapping(value = "/cares.html",method = RequestMethod.POST)
    @ResponseBody
    public PageSupport<Care> cares(Care care,
                                   @RequestParam(required = false,defaultValue = "1") Integer pageIndex)
    {
        PageSupport<Care> pageSupport=new PageSupport<Care>();
        pageIndex=(pageIndex-1)* Myfinal.PAGESIZE;
        List<Care> careList = careService.findCareByUserId(care, pageIndex, Myfinal.PAGESIZE);
        pageSupport.setDataList(careList);
        pageSupport.setPageIndex(pageIndex);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        int totalCount = careService.findCareCountByUserId(care);
        pageSupport.setTotalCount(totalCount);
        return pageSupport;
    }


    //显示客户关怀列表界面
    @RequestMapping("/care.html")
    public String care(Care care, HttpSession session)
    {
        User user = userService.findUserById(care.getUserId());
        session.setAttribute("user",user);
        return "sys/care/care";
    }
}
