package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.User;
import com.crmsystem.crm.service.EmpService;
import com.crmsystem.crm.service.UserService;
import com.crmsystem.crm.util.Myfinal;
import com.crmsystem.crm.util.PageSupport;
import com.crmsystem.crm.util.UserCondition;
import org.apache.ibatis.annotations.Param;
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

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-06 22:43
 * @version:第1版
 * @description:用户表控制器
 **/
@Controller
@RequestMapping("sys")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    EmpService empService;
    //处理修改客户
    @RequestMapping(value = "/updateUser.html",method = RequestMethod.POST)
    public String updateUser(User user,Model model)
    {
        int rel = userService.updateUserByEmp(user);
        if(rel>0)
        {
            System.out.println("修改成功");
            return "redirect:/sys/getUsers.html";
        }else {
            model.addAttribute("message","修改错误");
            return "sys/err";
        }
    }

    //销售代表删除客户
    @RequestMapping("/delUserByEmp.html")
    @ResponseBody
    public String delUserByEmp(User user)
    {
        user.setEmpCode("");
        int rel = userService.delUserByEmp(user);
        if(rel>0)
        {
            return "1";
        }else {
            return "-1";
        }
    }

    //处理添加界面
    @RequestMapping(value = "/addUser.html",method = RequestMethod.POST)
    public String addUser(User user,HttpSession session)
    {
        Emp emp = (Emp)session.getAttribute("session");
        user.setEmpCode(emp.getEmpCode());
        user.setUserType("");
        user.setCreditGrade("");
        Date date=new Date();
        user.setAddTime(date);
        int rel = userService.addUser(user);
        if(rel>0){
            return  "redirect:/sys/getUsers.html";
        }else{
            return "sys/user/addUser";
        }
    }
    //显示添加界面
    @RequestMapping("/addUser.html")
    public String addUser()
    {
        return "sys/user/addUser";
    }

    //异步显示部门经理查看客户的信息
    @RequestMapping("/userByDerictor.html")
    @ResponseBody
    public PageSupport<User> userByDerictor(User user,@RequestParam(required = false,defaultValue = "1") Integer pageIndex)
    {
        PageSupport<User> pageSupport=new PageSupport<User>();
        user.setEmpCode("");
        pageIndex=(pageIndex-1)*Myfinal.PAGESIZE;
        List<User> userList = userService.findUsersByDerictor(user, pageIndex, Myfinal.PAGESIZE);
        pageSupport.setDataList(userList);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        pageSupport.setPageIndex(pageIndex);
        int totalCount = userService.findUserCountByDerictor(user);
        pageSupport.setTotalCount(totalCount);
        return pageSupport;
    }
    //异步显示部门经理查看客户的信息
    @RequestMapping("/userByManager.html")
    @ResponseBody
    public PageSupport<User> userByManager(User user,@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                                           HttpSession session)
    {
        PageSupport<User> pageSupport=new PageSupport<User>();
        Emp emp = (Emp) session.getAttribute("session");
        pageIndex=(pageIndex-1)*Myfinal.PAGESIZE;
        List<User> userList = userService.findUsersByManager(emp.getDeptId(), user, pageIndex, Myfinal.PAGESIZE);
        pageSupport.setDataList(userList);
        pageSupport.setPageIndex(pageIndex);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        int totalCount = userService.findUserCountByManager(emp.getDeptId(), user);
        pageSupport.setTotalCount(totalCount);
        return pageSupport;
    }
    //异步显示销售代表客户的信息
    @RequestMapping("/userByEmp.html")
    @ResponseBody
    public PageSupport<User> userByEmp(User user,@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                                HttpSession session)
    {
        PageSupport<User> pageSupport=new PageSupport<User>();
        Emp emp1 = (Emp) session.getAttribute("session");
        user.setEmpCode(emp1.getEmpCode());
        pageIndex=(pageIndex-1)*Myfinal.PAGESIZE;
        List<User> userList = userService.findUsersByEmp(user, pageIndex, Myfinal.PAGESIZE);
        pageSupport.setDataList(userList);
        pageSupport.setPageIndex(pageIndex);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        int totalCount = userService.findUserCountByEmp(user);
        pageSupport.setTotalCount(totalCount);
        return pageSupport;
    }
    //显示客户界面
    @RequestMapping("/getUsers.html")
    public String users(HttpSession session)
    {
        Emp emp = (Emp)session.getAttribute("session");
        if(emp.getRolesId()==4)
        {
            return "sys/user/userByEmp";
        }
        if(emp.getRolesId()==3)
        {
            return "sys/user/userByManager";
        }
        if(emp.getRolesId()==2)
        {
            return "sys/user/userByDerictor";
        }
        return "redirect:/sys/err.html";
    }



    //批量修改客户池信息
    @RequestMapping("/updateLeaveUsers.html")
    public String updateLeaveUser(String empCode,Integer[] userIds,Model model)
    {
        int rel;
        for(Integer userId:userIds)
        {
            rel = userService.updateLeaveUsers(empCode, userId);
            if(rel<1)
            {
                model.addAttribute("message","出现修改错误");
                return "sys/err.html";
            }
        }
        return "redirect:/sys/leaveUser.html";
    }
    //异步显示客户池信息
    @RequestMapping("/leaveUsers.html")
    @ResponseBody
    public PageSupport<User> leaveUser(@RequestParam(required = false,defaultValue = "1") Integer pageIndex)
    {
        PageSupport pageSupport=new PageSupport();
        pageIndex=(pageIndex-1)*Myfinal.PAGESIZE;
        List<User> leaveList = userService.findUsersPageByEmpCode("", pageIndex, Myfinal.PAGESIZE);
        pageSupport.setDataList(leaveList);
        pageSupport.setPageIndex(pageIndex);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        int totalCount = userService.findUsersCountByEmpCode("");
        pageSupport.setTotalCount(totalCount);
        return pageSupport;
    }
    //显示客户池界面
    @RequestMapping("/leaveUser.html")
    public String leaveUser(Model model, HttpSession session)
    {
        Emp emp = (Emp)session.getAttribute("session");
        if(emp.getRolesId()!=2)
        {
            return "redirect:/sys/err.html";
        }
        List<Emp> empList = empService.findAllEmp(Myfinal.OFF_JOB,4);
        model.addAttribute("empList",empList);
        return "sys/user/leaveUser";
    }
    //根据客户ID查询客户信息
    @RequestMapping("/findUserName.html")
    @ResponseBody
    public User findUser(Integer userId,Model model)
    {
        System.out.println("进入方法");
        User user = userService.findUserById(userId);
        model.addAttribute("user",user);
        return user;
    }

    //异步 分页 检索 获得用户列表
    @RequestMapping(value = "/user.html",method = RequestMethod.POST)
    @ResponseBody
    public Object doUserList(UserCondition userCondition,
                             @RequestParam(value = "pageIndex",required = true,defaultValue = "1") Integer pageIndex){
        PageSupport<User> userPage = new PageSupport<User>();
        if(userCondition==null || userCondition.equals("")){
            userCondition = new UserCondition();
        }
        int pageSize = Myfinal.pageSize;
        List<User> userlist = userService.findUserPaging(userCondition,pageIndex,pageSize);
        int totalCount = userService.findUserCount(userCondition);
        userPage.setPageIndex(pageIndex);
        userPage.setPageSize(pageSize);
        userPage.setTotalCount(totalCount);
        userPage.setDataList(userlist);
        return userPage;
    }

}
