package com.crmsystem.crm.entity;
/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-04 11:32
 * @version:第1版
 * @description:公告表持久化类
 **/

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {

    private Integer noticeId; //公告id
    private String title; //标题
    private String theme; //主题
    private String mains; //正文
    private Date publishTime; //发布时间
    private String empCode; //发布者编号
    private String states; //状态


    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }


    public String getMains() {
        return mains;
    }

    public void setMains(String mains) {
        this.mains = mains;
    }


    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }


    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

}
