package com.crmsystem.crm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-08 12:20
 * @version:第1版
 * @description:异常界面
 **/
@Controller
@RequestMapping("sys")
public class ErrController {
    @RequestMapping("/err.html")
    public String err()
    {
        System.out.println("异常");
        return "sys/noRight";
    }

}
