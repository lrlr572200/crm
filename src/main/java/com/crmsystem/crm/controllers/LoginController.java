package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.Notice;
import com.crmsystem.crm.entity.Plan;
import com.crmsystem.crm.entity.Serve;
import com.crmsystem.crm.service.*;
import com.crmsystem.crm.util.Myfinal;
import com.crmsystem.crm.util.NoticeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-04 19:33
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
@Controller
public class LoginController {
    @Resource
    RolesService rolesService;
    @Resource
    EmpService empService;
    @Resource
    NoticeService noticeService;
    @Resource
    RightService rightService;
    @Resource
    SystemService systemService;
    @Resource
    ServeService serveService;
    @Resource
    PlanService planService;
    //显示登录
    @RequestMapping(value = "/login.html",method = RequestMethod.GET)
    public String login()
    {
        return "login";
    }

    //注销
    @RequestMapping("loginOut.html")
    public String loginOut(HttpSession session)
    {
        session.removeAttribute("session");
        return "login";
    }

    //处理登录
    @RequestMapping(value = "/login.html",method = RequestMethod.POST)
    public String login(String empCode, String password, Model model,HttpSession session)
    {
        Emp emp = empService.login(empCode, password);
        if(emp==null)
        {
            model.addAttribute("message","用户名或密码错误");
            return "login";
        }else{
            if(emp.getStates().equals(Myfinal.OFF_JOB))
            {
                model.addAttribute("message","抱歉！您已离职");
                return "sys/err";
            }else{
                //获取权限等级
                int grade=rightService.findGradeByRolesId(emp.getRolesId());
                String rolesName = rolesService.findRolesNameById(emp.getRolesId());
                session.setAttribute("rolesName",rolesName);
                session.setAttribute("session",emp);
                session.setAttribute("grade",grade);
                if(grade==100)
                {
                    return "redirect:sys/indexManager.html";
                }
                if(emp.getRolesId()==3)
                {
                    Plan plan=new Plan();
                    plan.setDeptId(emp.getDeptId());
                    plan.setStates("已打回");
                    plan.setNextCode(emp.getEmpCode());
                    Integer con = planService.findCountByStates(plan);
                    if (con==null || "".equals(con)){
                        con=0;
                    }
                    session.setAttribute("con",con);
                }else if(emp.getRolesId()==4){
                    Plan plan=new Plan();
                    plan.setEmpCode(emp.getEmpCode());
                    plan.setStates("已打回");
                    Integer con = planService.findCountByStates(plan);
                    if (con==null || "".equals(con)){
                        con=0;
                    }
                    session.setAttribute("con",con);
                }else {
                    session.setAttribute("con",0);
                }
                return "redirect:sys/index.html";
            }
        }
    }
    //普通用户显示首页
    @RequestMapping(value = "sys/index.html",method = RequestMethod.GET)
    public String index()
    {
        return "sys/index";
    }

    //管理员用户显示首页
    @RequestMapping(value = "sys/indexManager.html",method = RequestMethod.GET)
    public String indexManager()
    {
        return "sys/indexManager";
    }

    //普通用户显示首页框架
    @RequestMapping(value = "sys/home.html",method = RequestMethod.GET)
    public String home(Model model,HttpSession session)
    {
        //获取首页公告显示
        NoticeUtil notice=new NoticeUtil();
        List<NoticeUtil> noticeList = noticeService.findNoticePage(Myfinal.pageSize,1,notice);
        model.addAttribute("noticeList",noticeList);
        //获取当前登录对象
        Emp emp = (Emp)session.getAttribute("session");
        Serve serve=new Serve();
        Serve serve1=new Serve();
        if(emp.getRolesId()==3)
        {
            serve.setStates("已提交");
            serve.setDeptId(emp.getDeptId());
            List<Serve> serveList = serveService.findServeOnIndex(emp, serve,serve1, 0, Myfinal.pageSize);
            model.addAttribute("serveList",serveList);
            serve.setStates("已完成");
            serve.setStates("已完成");
            List<Serve> serveList1= serveService.findServeOnIndex(emp, serve,serve1, 0, Myfinal.pageSize);
            model.addAttribute("serveList1",serveList1);
            return "sys/home";
        }
        if(emp.getRolesId()==4)
        {
            serve.setStates("已分配");
            serve1.setStates("已打回");
            serve.setEmpCode(emp.getEmpCode());
            serve.setDeptId(emp.getDeptId());
            List<Serve> serveList = serveService.findServeOnIndex(emp, serve,serve1, 0, Myfinal.pageSize);
            model.addAttribute("serveList",serveList);
            serve.setStates("已完成");
            serve.setStates("已完成");
            List<Serve> serveList1= serveService.findServeOnIndex(emp, serve,serve1, 0, Myfinal.pageSize);
            model.addAttribute("serveList1",serveList1);
            return "sys/home";
        }
        return "sys/home";
    }


}
