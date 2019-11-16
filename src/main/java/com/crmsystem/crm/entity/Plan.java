package com.crmsystem.crm.entity;

import java.io.Serializable;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-04 11:32
 * @version:第1版
 * @description:销售计划表持久化类
 **/

public class Plan implements Serializable {

    private Integer planId; //计划id
    private String content; //计划内容
    private String empCode; //计划创建者
    private String createEmpName; //计划创建者的姓名
    private double planMoney; //计划销售金额
    private String planYear; //年份
    private String planMonth; //月份
    private String states; //状态
    private String nextCode; //下一审核人
    private String auditEmpName; //下一审核者姓名
    private Integer deptId; //部门编号
    private String deptName; //部门名称
    private Integer planAdd; //计划发展客户数量
    private double salesMoney; //预测销售额
    private double realMoney; //当月实际销售额
    private String checkGrade; //考核评级
    private String remark; //备注


    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getCreateEmpName() {
        return createEmpName;
    }

    public void setCreateEmpName(String createEmpName) {
        this.createEmpName = createEmpName;
    }

    public double getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(double planMoney) {
        this.planMoney = planMoney;
    }

    public String getPlanYear() {
        return planYear;
    }

    public void setPlanYear(String planYear) {
        this.planYear = planYear;
    }

    public String getPlanMonth() {
        return planMonth;
    }

    public void setPlanMonth(String planMonth) {
        this.planMonth = planMonth;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getNextCode() {
        return nextCode;
    }

    public void setNextCode(String nextCode) {
        this.nextCode = nextCode;
    }

    public String getAuditEmpName() {
        return auditEmpName;
    }

    public void setAuditEmpName(String auditEmpName) {
        this.auditEmpName = auditEmpName;
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

    public Integer getPlanAdd() {
        return planAdd;
    }

    public void setPlanAdd(Integer planAdd) {
        this.planAdd = planAdd;
    }

    public double getSalesMoney() {
        return salesMoney;
    }

    public void setSalesMoney(double salesMoney) {
        this.salesMoney = salesMoney;
    }

    public double getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(double realMoney) {
        this.realMoney = realMoney;
    }

    public String getCheckGrade() {
        return checkGrade;
    }

    public void setCheckGrade(String checkGrade) {
        this.checkGrade = checkGrade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
