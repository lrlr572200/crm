package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.User;
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
    //根据客户ID查询客户信息
    @RequestMapping("/findUserName.html")
    @ResponseBody
    public User findUser(Integer userId,Model model)
    {
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
