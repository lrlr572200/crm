package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.Serve;
import com.crmsystem.crm.service.ServeService;
import com.crmsystem.crm.service.SystemService;
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
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-10 14:22
 * @version:第1版
 * @description:此处添加对该类的说明
 * @author: Star-GuoqingLi
 * @create: 2019-11-11 19:42
 * @version:第1版
 * @description:
 **/
@Controller
@RequestMapping("sys")
public class ServeController {
    @Resource
    SystemService systemService;
    @Resource
    private ServeService serveService;
    //显示添加界面
    @RequestMapping("/addServe.html")
    public  String addServe(Model model)
    {
        String value = systemService.findChanValue("服务分配变量");
        model.addAttribute("value",value);
        return "sys/serve/addServe";
    }
    //处理添加界面
    @RequestMapping(value = "/addServes.html",method = RequestMethod.POST)
    public  String addServe(Serve serve,HttpSession session,String value
                            ,Model model)
    {
        Emp emp = (Emp)session.getAttribute("session");
        System.out.println("添加人："+emp.getEmpCode());
        serve.setAddCode(emp.getEmpCode());
        serve.setStates("已提交");
        serve.setMeet("");
        Date date=new Date();
        serve.setAddTime(date);
        int rel=serveService.addServe(serve);
        if(rel>0)
        {
            Integer v=Integer.parseInt(value);
            v++;
            if(v>3)
            {
                v=1;
            }
            String aaa=v.toString();
            systemService.updateChanValue(aaa,"服务分配变量");
            return "redirect:/sys/serve.html";
        }else{
            return "sys/serve/addServe";
        }

    }
    //显示服务界面
    @RequestMapping("/serve.html")
    public String serve(HttpSession session)
    {
        int grade = (int)session.getAttribute("grade");
        if(grade!=2)
        {
            return "redirect:/sys/err.html";
        }
        return "sys/serve/serve";
    }
    //异步显示分页过滤服务
    @RequestMapping(value = "/serves.html",method = RequestMethod.POST)
    @ResponseBody
    public PageSupport<Serve> showServe(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                                        Model model, HttpSession session,Serve serve)
    {
        PageSupport<Serve> pageSupport=new PageSupport<Serve>();
        pageIndex=(pageIndex-1)*Myfinal.PAGESIZE;
        List<Serve> serveList = serveService.findServePage(pageIndex, Myfinal.PAGESIZE, serve);
        pageSupport.setPageIndex(pageIndex);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        pageSupport.setDataList(serveList);
        int totalCount=serveService.findServeCount(serve);
        pageSupport.setTotalCount(totalCount);
        model.addAttribute("pageSupport",pageSupport);
        return pageSupport;
    }




    //显示我的服务页面
    @RequestMapping(value = "/my_serve.html")
    public String getMyServe(){
        return "sys/serve/my_serve";
    }

    //处理我的服务分页检索的异步方法
    @RequestMapping(value = "/myServe.ajax")
    @ResponseBody
    public Object doMyServe(Serve serve,
                            @RequestParam(value = "pageIndex",required = true,defaultValue = "0") Integer pageIndex,
                            @RequestParam(value = "pageSize",required = true,defaultValue = "5") Integer pageSize){
        Map<Object,Object> map = new HashMap<Object, Object>();
        if (serve==null || "".equals(serve)){
            serve = new Serve();
        }
        int total = serveService.fintServeCot(serve);
        List<Serve> serveList = serveService.findServePaging(serve,pageIndex,pageSize);
        map.put("total",total);
        map.put("data",serveList);
        return map;
    }


    //修改我的服务的方法
    @RequestMapping(value = "/updServe.ajax")
    @ResponseBody
    public Object doUpdMyServe( Serve serve){
        if(serve==null || "".equals(serve)){
            serve=new Serve();
        }
        int sign = serveService.updServe(serve);
        return sign;
    }

}
