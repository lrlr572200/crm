package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.Notice;
import com.crmsystem.crm.service.NoticeService;
import com.crmsystem.crm.util.Myfinal;
import com.crmsystem.crm.util.NoticeUtil;
import com.crmsystem.crm.util.PageSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-05 09:24
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
@Controller
@RequestMapping("sys")
public class NoticeController {
    @Resource
    NoticeService noticeService=null;

    //显示公告界面
    @RequestMapping(value = "/notices.html",method = RequestMethod.GET)
    public String showNotice(Model model, HttpSession session)
    {
        return "sys/notice/notice";
    }

    //异步获取已发布公告
    @RequestMapping(value = "/notice.html",method = RequestMethod.POST)
    @ResponseBody
    public PageSupport<NoticeUtil> showNotice(Model model, HttpSession session, NoticeUtil notice,
                                              @RequestParam(required = false,defaultValue = "1") Integer pageIndex)
    {
        PageSupport<NoticeUtil> pageSupport=new PageSupport<NoticeUtil>();
        pageIndex=(pageIndex-1)*Myfinal.PAGESIZE;
        notice.setStates("已发布");
        List<NoticeUtil> noticeList = noticeService.findNoticePage(Myfinal.PAGESIZE, pageIndex, notice);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        pageSupport.setPageIndex(pageIndex);
        Integer totalCount=noticeService.findNoticeCount(notice);
        pageSupport.setTotalCount(totalCount);
        pageSupport.setDataList(noticeList);
        model.addAttribute("pageSupport",pageSupport);
        return pageSupport;
    }

    //查看一个公告
    @RequestMapping("/anotice.html")
    public String anotice(Model model,Integer noticeId)
    {
        NoticeUtil notice = noticeService.findANotice(noticeId);
        model.addAttribute("notice",notice);
        return "sys/notice/anotice";
    }

    //显示添加界面
    @RequestMapping("/addNotice.html")
    public String addNotice(HttpSession session)
    {
        return "sys/notice/write_email";
    }
    //处理添加界面
    @RequestMapping(value = "/addNotice.html",method = RequestMethod.POST)
    public String addNotice(Model model,Notice notice)
    {
        Date date=new Date();
        notice.setPublishTime(date);
        int rel = noticeService.addNotice(notice);
        if(rel>0)
        {
            return "redirect:/sys/notices.html";
        }else{
            return "sys/notice/write_email";
        }

    }

    //处理删除请求
    @RequestMapping(value = "/delNotice.html",method = RequestMethod.POST)
    @ResponseBody
    public String delNotice(Model model,Integer noticeId,HttpSession session)
    {
        int grade = (int)session.getAttribute("grade");
        if(grade<5)
        {
            return "-1";
        }
        int rel=noticeService.delNotice(noticeId);
        if(rel>0)
        {
            return "1";
        }else {
            return "0";
        }
    }
    //显示未发布界面
    @RequestMapping(value = "/notice2.html",method = RequestMethod.GET)
    public String notice2()
    {
        return "sys/notice/notice2";
    }

    //异步获取未发布公告
    @RequestMapping(value = "/notice2.html",method = RequestMethod.POST)
    @ResponseBody
    public PageSupport<NoticeUtil> show2Notice(Model model, HttpSession session, NoticeUtil notice,
                                              @RequestParam(required = false,defaultValue = "1") Integer pageIndex)
    {
        PageSupport<NoticeUtil> pageSupport=new PageSupport<NoticeUtil>();
        pageIndex=(pageIndex-1)*Myfinal.PAGESIZE;
        notice.setStates("新创建");
        Emp emp = (Emp)session.getAttribute("session");
        notice.setEmpName(emp.getEmpName());
        List<NoticeUtil> noticeList = noticeService.findNoticePage(Myfinal.PAGESIZE, pageIndex, notice);
        pageSupport.setPageSize(Myfinal.PAGESIZE);
        pageSupport.setPageIndex(pageIndex);
        Integer totalCount=noticeService.findNoticeCount(notice);
        pageSupport.setTotalCount(totalCount);
        pageSupport.setDataList(noticeList);
        model.addAttribute("pageSupport",pageSupport);
        return pageSupport;
    }

    //显示修改界面
    @RequestMapping("/updateNotice.html")
    public String updateNotice(Integer noticeId,Model model)
    {
        NoticeUtil notice = noticeService.findANotice(noticeId);
        model.addAttribute("notice",notice);
        return "sys/notice/updateNotice";
    }
    //处理修改界面
    @RequestMapping(value = "/updateNotice.html",method = RequestMethod.POST)
    public String updateNotice(Notice notice, Model model)
    {
        Date date=new Date();
        notice.setPublishTime(date);
        int rel=noticeService.updateNotice(notice);
        System.out.println("返回值："+rel);
        if(rel>0)
        {
            if(notice.getStates().equals("新创建"))
            {
                return "redirect:/sys/notice2.html";
            }else {
                return "redirect:/sys/notices.html";
            }
        }else {
            return "sys/notice/updateNotice";
        }

    }
}
