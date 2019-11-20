package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.service.EmpService;
import com.crmsystem.crm.service.RightService;
import com.crmsystem.crm.util.EmailElement;
import com.crmsystem.crm.util.MailClass;
import com.crmsystem.crm.util.Myfinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
    private EmpService empService;
    @Resource
    private RightService rightService;

    //通过注入的方式获得邮件任务工具类
    @Autowired
    private MailClass mail;

    //从全局配置文件中读取数据并赋值给path变量
    @Value("${web.upload-path}")
    private String path;



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
            }else {
                emp.setHead("../"+emp.getHead());
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
    @RequestMapping(value = "/add_emp.html")
    public String getAddEmp(Model model){
        //调用员工编码生成器
        Date date = new Date();
        Long time = date.getTime();
        String empCode =  time.toString();
        //调用密码生成器
        String password= Myfinal.PWD;
        Emp emp = new Emp();
        emp.setEmpCode(empCode);
        emp.setPassword(password);
        model.addAttribute("emp",emp);

        return "sys/emp/add_emp";
    }

    //处理添加员工
    @SuppressWarnings("all")
    @RequestMapping(value = "/addEmp.html")
    public String doAddEmp(HttpSession session, Emp emp, Model model,
                           @RequestParam(value = "myPic",required = false) MultipartFile myPic){
        //获取当前操作人权限
        Integer grade =(Integer)session.getAttribute("grade");
        //权限验证
        if (grade==null || "".equals(grade)){
            return "redirect:sys/err.html";
        }
        if (grade!=100){
            return "redirect:sys/err.html";
        }

        //文件上传
        if(!myPic.isEmpty()){ //如果上传文件不为空
            //获取文件名
            String fileName = myPic.getOriginalFilename();
            //截取旧文件名后缀
            int suf = fileName.lastIndexOf(".");
            int fileNameLength = fileName.length();
            String suffix =fileName.substring(suf,fileNameLength);
            //指定新文件名
            String newFileName = emp.getEmpCode()+suffix;
            //指定路径，指定服务器上的物理路径
            //String path = "F:/pic";
            File filel = new File(path+"/"+newFileName);
            try {
                if (!filel.getParentFile().exists()){ //如果没有指定目录
                    filel.getParentFile().mkdirs(); //级联创建目录
                }
                myPic.transferTo(filel);
                System.out.println("添加成功");
                //将文件名保存到实体中
                emp.setHead(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("添加失败");
            }
        }//文件上传结束
        emp.setEntryTime(new Date()); //赋值入职时间为当前时间
        int sign = empService.addEmp(emp);
        if (sign>0){
            //定义邮件要素
            EmailElement ee = new EmailElement();
            String theme=ee.getAddtheme(); //邮件主题
            String content = ee.getAddcontent(emp.getEmpName(), emp.getEmpCode(), emp.getPassword());
            String[] recipients = {emp.getEmail()};
            String addresser = ee.getAddresser();
            //添加成功后给员工发送邮件
            mail.fileEmail(theme,content,addresser,recipients,null,null);
            return "redirect:/sys/userManage.html";
        }else {
            model.addAttribute("msg","添加失败！请退出重试！");
            return "sys/emp/add_emp";
        }
    }

    //跳转到修改的页面
    @RequestMapping(value = "/upd_emp.html")
    public Object getUpdEmp(@RequestParam(value = "empCode",required = true) String empCode,
                            Model model){
        System.out.println(empCode);
        Emp e = new Emp();
        e.setEmpCode(empCode);
        List<Emp> emps = empService.findEmpPaging(e,0,1);
        e=emps.get(0);
        model.addAttribute("emp",e);

        return "sys/emp/upd_emp";
    }

    //处理修改的方法
    @SuppressWarnings("all")
    @RequestMapping(value = "/updemp.html")
    public Object doUpdEmp(HttpSession session, Emp emp,Model model,
                           @RequestParam(value = "myPic",required = false) MultipartFile myPic){
        if (emp==null || "".equals(emp)){
            throw  new  RuntimeException("修改时传值错误！");
        }
        //文件上传
        if(!myPic.isEmpty()){ //如果上传文件不为空
            //获取文件名
            String fileName = myPic.getOriginalFilename();
            //截取旧文件名后缀
            int suf = fileName.lastIndexOf(".");
            int fileNameLength = fileName.length();
            String suffix =fileName.substring(suf,fileNameLength);
            //指定新文件名
            String newFileName = emp.getEmpCode()+suffix;
            //指定路径，指定服务器上的物理路径
            //String path = "F:/pic";
            File filel = new File(path+"/"+newFileName);
            //删除旧文件
            File oldfilel = new File(path+"/"+emp.getHead());
            if(oldfilel.exists()){
                oldfilel.delete();
                System.out.println(emp.getHead()+"文件删除成功！");
            }
            try {
                if (!filel.getParentFile().exists()){ //如果没有指定目录
                    filel.getParentFile().mkdirs(); //级联创建目录
                }
                myPic.transferTo(filel);
                System.out.println("添加成功");
                //将文件名保存到实体中
                emp.setHead(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("添加失败");
            }
        }//文件上传结束
        int sign = empService.updEmpById(emp);
        if (sign>0){
            return "redirect:/sys/userManage.html";
        }else {
            model.addAttribute("msg","添加失败！请退出重试！");
            return "sys/emp/upd_emp";
        }
    }

    //处理删除的方法
    @RequestMapping("/del_emp.ajax")
    @ResponseBody
    public Object doDelEmp(String [] empCodeArr,HttpSession session){
        //获取当前操作人权限
        Integer grade =(Integer)session.getAttribute("grade");
        //权限验证
        if (grade==null || "".equals(grade)){
            return "redirect:sys/err.html";
        }
        if (grade!=100){
            return "redirect:sys/err.html";
        }
        Emp emp = (Emp) session.getAttribute("session");
        int sign = 0;
        File oldfilel =null;
        Integer right = 0;
        List<Emp> emps = new ArrayList<Emp>();
        Emp emp1 = null;
        for (String e:empCodeArr){
            emp1 = empService.findEmpByCode(e);
            emps.add(emp1);
            if (emp.getEmpCode()==emp1.getEmpCode()){
                emps = null;
                throw  new  RuntimeException("不能删除自己！");
            }
            right=rightService.findGradeByRolesId(emp1.getRolesId());
            if (right==100){
                emps = null;
                throw  new  RuntimeException("该项操作超出权限等级!");
            }
        }
        for (Emp e:emps){
            if (e.getHead()!=null && !"".equals(e.getHead())){
                oldfilel = new File(path+"/"+e.getHead());
                if(oldfilel.exists()){
                    oldfilel.delete();
                    System.out.println(e.getHead()+"文件删除成功！");
                }
            }
            sign=empService.delEmpById(e.getEmpCode());
        }
        return sign;
    }


}
