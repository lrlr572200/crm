package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Chan;
import com.crmsystem.crm.entity.Emp;

import com.crmsystem.crm.service.ChanService;
import com.crmsystem.crm.service.EmpService;
import com.crmsystem.crm.util.PageSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crmsystem.crm.service.SystemService;
import com.crmsystem.crm.util.Myfinal;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁、郝存凯
 * @create: 2019-11-08 18:37
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
@Controller
@RequestMapping("sys")
public class ChanController {
    @Resource
    ChanService chanService;
    @Resource
    SystemService systemService;
    @Resource
    EmpService empService;
    //显示机会界面
    @RequestMapping("/chan.html")
    public String chan(HttpSession session)
    {
        int grade = (int)session.getAttribute("grade");
        if(grade!=2)
        {
            return "redirect:/sys/err.html";
        }
        return "sys/chan/chan";
    }


    //显示机会信息
    @RequestMapping(value = "/chan_info.html")
    public  String getChan(HttpSession session,Model model){
        Emp emp=(Emp) session.getAttribute("session");
        Integer deptId=emp.getDeptId();
        emp.setDeptId(deptId);
        List<Emp> empList=empService.findEmpInfo(emp);
        model.addAttribute("empList",empList);
        Integer roles=emp.getRolesId();
        if (roles==3) {
            return "/sys/information/chan_info";
        }else{
            return "sys/noRight";
        }
    }
    //处理客户资源管理分页
    @RequestMapping(value = "/chan_info.ajax")
    @ResponseBody
    public Object doChanByDidpaging(@RequestParam(value = "pageIndex",required = true,defaultValue = "1") Integer pageIndex,
                                    @RequestParam(value = "pageSize",required = true,defaultValue = "5")Integer pageSize,
                                    HttpSession session, Model model){
        Emp emp=(Emp) session.getAttribute("session");
        Integer deptId=emp.getDeptId();
        List<Chan> chanList=chanService.findChanByDid(deptId,pageIndex,pageSize);
        Integer totalCount=chanService.findChanCountByDid(deptId);
        PageSupport<Chan> chanPage=new PageSupport<>(pageIndex,pageSize,totalCount,chanList);
        Map<Object,Object> map=new HashMap();
        map.put("total",totalCount);
        map.put("data",chanList);
        return  map;
    }

    //异步显示机会
    @RequestMapping(value = "/chans.html",method = RequestMethod.POST)
    @ResponseBody
    public PageSupport<Chan> showChan(@RequestParam(required = false,defaultValue = "1")
                  Integer pageIndex, Chan chan, Model model, HttpSession session)
    {
        PageSupport<Chan> pageSupport=new PageSupport<Chan>();
        pageIndex= (pageIndex-1)*Myfinal.PAGESIZE;
        List<Chan> chanList = chanService.findChanPage(pageIndex, Myfinal.PAGESIZE, chan);
        pageSupport.setDataList(chanList);
        pageSupport.setPageIndex(pageIndex);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        int totalCount = chanService.findChanCount(chan);
        pageSupport.setTotalCount(totalCount);
        model.addAttribute("pageSupport",pageSupport);
        return pageSupport;
    }

    //异步分页检索显示我的机会的方法--李国庆
    @RequestMapping(value = "/myChan.ajax")
    @ResponseBody
    public Object doMyChan(Chan chan,
                           @RequestParam(value = "pageIndex",required = true,defaultValue = "0") Integer pageIndex,
                           @RequestParam(value = "pageSize",required = true,defaultValue = "5") Integer pageSize){
        Map<Object,Object> map = new HashMap<Object,Object>();
        if (chan==null || "".equals(chan)){
            chan = new Chan();
        }
        int total = chanService.findChanCot(chan);
        List<Chan> chanList = chanService.findChanPaging(pageIndex,pageSize,chan);
        map.put("total",total);
        map.put("data",chanList);
        return map;
    }

    //查看机会来源
    @RequestMapping(value = "/getSource.ajax",method = RequestMethod.GET)
    @ResponseBody
    public Object getsource(){
        List<String> source = chanService.findSource();
        return source;
    }

    //显示添加界面
    @RequestMapping("/addChan.html")
    public String addChan(Model model)
    {
        String value = systemService.findChanValue("机会分配变量");
        model.addAttribute("value",value);
        return "sys/chan/addChan";
    }

    //处理添加界面
    @RequestMapping(value = "/addChans.html",method = RequestMethod.POST)
    public String addChan(Chan chan,Model model,HttpSession session,String value)
    {
        Date date=new Date();
        chan.setAddTime(date);
        Emp emp = (Emp)session.getAttribute("session");
        chan.setAddCode(emp.getEmpCode());
        chan.setStates("未分配");
        int rel = chanService.addChan(chan);
        if(rel>0)
        {
            Integer v=Integer.parseInt(value);
            v++;
            if(v>3)
            {
                v=1;
            }
            String aaa=v.toString();
            systemService.updateChanValue(aaa,"机会分配变量");
            return "redirect:/sys/chan.html";
        }else{
            return "sys/chan/addChan";
        }
    }
    //修改分配资源
    @RequestMapping(value = "/updateChan.ajax")
    @ResponseBody
    public Object updateChan(Chan chan){
        if (chan==null || "".equals(chan)){
            chan=new Chan();
        }
        int sign=chanService.updateChan(chan);
        return sign;
    }

    //跳转到我的机会的页面
    @RequestMapping(value = "/my_chan.html")
    public String getMyChan(){
        return "sys/chan/my_chan";
    }

}
