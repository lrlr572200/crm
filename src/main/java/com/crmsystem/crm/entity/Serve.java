package com.crmsystem.crm.entity;
/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-04 11:32
 * @version:第1版
 * @description:服务表持久化类
 **/

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Serve implements Serializable {

    private Integer serveId; //服务id
    private String addCode; //服务单创建者
    private String empName;//服务创建者姓名   工具属性,数据库无此字段
    private String details; //服务详情
    private String states; //状态
    private String empCode; //解决人编号
    private String solvePeople;
    private String meet; //客户是否满意
    private String userName; //客户姓名
    private String phone; //客户联系电话
    private Integer deptId; //服务所属部门
    private String deptName;//服务所属部门名称   工具属性，数据库无此字段
    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addTime; //服务创建时间
    private String remark; //备注

    public Integer getServeId() {
        return serveId;
    }

    public void setServeId(Integer serveId) {
        this.serveId = serveId;
    }

    public String getAddCode() {
        return addCode;
    }

    public void setAddCode(String addCode) {
        this.addCode = addCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getSolvePeople() {
        return solvePeople;
    }

    public void setSolvePeople(String solvePeople) {
        this.solvePeople = solvePeople;
    }

    public String getMeet() {
        return meet;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
