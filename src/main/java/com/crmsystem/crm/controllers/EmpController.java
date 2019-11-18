package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Dept;
import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.service.DeptService;
import com.crmsystem.crm.service.EmpService;
import com.crmsystem.crm.util.Myfinal;
import com.crmsystem.crm.util.PageSupport;
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
 * @author: Star-GuoqingLi
 * @create: 2019-11-05 11:35
 * @version:第1版
 * @description:员工表控制器
 **/
@Controller
@RequestMapping("sys")
public class EmpController {
    @Resource
    DeptService deptService;
    @Resource
    private EmpService empService;
    //异步显示同事资料
    @RequestMapping("/Colleagues.html")
    @ResponseBody
    public PageSupport<Emp> myColleague(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                                        Integer deptId, HttpSession session)
    {
        Emp emp = (Emp)session.getAttribute("session");
        pageIndex=(pageIndex-1)*Myfinal.PAGESIZE;
        List<Emp> empList = empService.findColleaguePage(emp.getEmpCode(), Myfinal.OFF_JOB, deptId, pageIndex, Myfinal.PAGESIZE);
        PageSupport<Emp> pageSupport=new PageSupport<Emp>();
        pageSupport.setDataList(empList);
        pageSupport.setPageIndex(pageIndex);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        Integer totalCount = empService.findColleagueCount(emp.getEmpCode(), Myfinal.OFF_JOB, deptId);
        pageSupport.setTotalCount(totalCount);
        return pageSupport;
    }
    //显示查看我的同事界面
    @RequestMapping("/Colleague.html")
    public String myColleague(Model model)
    {
        return "sys/emp/myColleague";
    }

    //跳转个人档案页面的方法
    @RequestMapping(value = "/dossier_my.html")
    public String get_dossier_my() {
        return "sys/emp/dossier_my";
    }

    //跳转我的资料页面的方法
    @RequestMapping(value = "/person_infor.html")
    public String get_myfile_data(@RequestParam(value = "empCode", required = true) String empCode, Model model) {
        Emp emp = empService.findEmpByCode(empCode);
        if (emp == null) {
            model.addAttribute("msg", "查无此人，请退出重新进入系统。。。。");
            return "login";
        } else {
            if ("".equals(emp.getHead()) || emp.getHead() == null) {
                emp.setHead("/images/image.jpg");
            }
            model.addAttribute("emp", emp);
            return "sys/emp/person_infor";
        }
    }

    //处理员工管理页面的分页检索功能
    @RequestMapping(value = "/empManage.ajax")
    @ResponseBody
    public Object doEmpManage(Emp emp,
                              @RequestParam(value = "pageIndex",required = true,defaultValue = "0") Integer pageIndex,
                              @RequestParam(value = "pageSize",required = true,defaultValue = "10")Integer pageSize){
        Map<Object,Object> map = new HashMap<Object, Object>();
        if (emp==null || "".equals(emp)){
            emp=new Emp();
        }
        int total=empService.findEmpCot(emp);
        List<Emp> data = empService.findEmpPaging(emp,pageIndex,pageSize);
        map.put("total",total);
        map.put("data",data);
        return map;
    }


    //查找员工状态
    @RequestMapping(value = "/getEmpStates.ajax")
    @ResponseBody
    public Object getEmpStates(){
        List<String> statesList = empService.findDeptStates();
        return statesList;
    }

    //跳转到添加员工的页面
    @RequestMapping(value = "add_emp.html")
    public String getAddEmp(){
        return "sys/emp/add_emp";
    }

    //添加员工
    @RequestMapping(value = "/addEmp.html")
    public String doAddEmp(){

        return "redirect:sys/userManage.html";
    }




}
