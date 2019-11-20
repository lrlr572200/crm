package com.crmsystem.crm.util;

import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-19 15:14
 * @version:第1版
 * @description:邮件任务工具类
 **/
@Component
public class MailClass {


    //引入日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入邮件组件
    @Autowired
    private JavaMailSenderImpl mailSender;



    //发送简单邮件，不支持html语言
    @Async //开启异步注解
    public void email(String theme,String content,String addresser,String [] recipients){
        //初始化工具
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject(theme);  //邮件主题
        message.setText(content); //邮件内容
        message.setTo(recipients); //收件人
        message.setFrom(addresser); //发件人

        //邮件发送
        try {
            mailSender.send(message);
            logger.info("主题为【"+theme+"】的邮件已经发送。");
        }catch (Exception e){
            logger.error("主题为【"+theme+"】的邮件发送异常！", e);
        }

    }

    //发送复杂邮件，支持html语言
    @Async  //开启异步注解
    public void fileEmail(String theme,String content,String addresser,
                          String [] recipients,String fileName,File file){
        try {
            //初始化工具
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            //邮件设置
            helper.setSubject(theme);  //邮件主题
            helper.setText(content,true); //邮件内容
            helper.setTo(recipients); //收件人
            helper.setFrom(addresser); //发件人
            if (file!=null){
                helper.addAttachment(fileName,file); //上传文件
            }
            //发送邮件
            mailSender.send(mimeMessage);
            logger.info("主题为【"+theme+"】的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("主题为【"+theme+"】的邮件发送异常！", e);
        }
    }


}
