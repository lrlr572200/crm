package com.crmsystem.crm.util;

import java.util.Date;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-06 21:48
 * @version:第1版
 * @description:myfile_data.html页面显示用户列表的检索条件工具类
 **/
public class UserCondition {

    private String empCode; //员工编号
    private String states; //用户状态
    private String userType; //用户类型
    private String source; //用户来源
    private String creditGrade; //用户信誉度
    private Date beginTime; //开始时间
    private Date endTime; //结束时间

    public UserCondition() {
    }

    public UserCondition(String empCode, String states, String userType, String source, String creditGrade, Date beginTime, Date endTime) {
        this.empCode = empCode;
        this.states = states;
        this.userType = userType;
        this.source = source;
        this.creditGrade = creditGrade;
        this.beginTime = beginTime;
        this.endTime = endTime;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(String creditGrade) {
        this.creditGrade = creditGrade;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
