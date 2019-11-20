package com.crmsystem.crm.util;

import java.io.File;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-19 16:11
 * @version:第1版
 * @description: 邮件要素工具类
 **/
public class EmailElement {

    //private String theme; //邮件主题
    //private String content; //邮件内容
    /*private String addresser; //发件人
    private String [] recipients; //收件人
    private String fileName; //文件名
    private File file; //文件; //文件*/

    private String addresser;
    //添加新员工时的邮件文本
    private String addtheme ;
    private String addcontent;


    public String getAddtheme() {
        return addtheme = "通知：硕果留存员工账号注册成功";
    }

    public String getAddcontent(String name,String code,String pwd) {
        return addcontent = "<h3 style='color:red;'>欢迎 "+name+" 加入我小组<h3>"
                +"<p>您的初始编号为：<span style='color:red;font-weight: bolder'>"+code
                +"</span>,账号可以修改一次。初始密码为：<span style='color:blue;font-weight: bolder'>"+pwd
                +"</span>。请尽快登陆系统修改维护个人信息。</P>";
    }

    public String getAddresser() {
        return addresser="1324004498@qq.com";
    }
}
