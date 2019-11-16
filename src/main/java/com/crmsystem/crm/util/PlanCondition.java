package com.crmsystem.crm.util;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-07 16:53
 * @version:第1版
 * @description:月销售计划表分页条件检索工具类
 **/
public class PlanCondition {

    private String empCode; //计划创建者
    private String planYear; //年份
    private String planMonth; //月份
    private String states; //状态
    private String nextCode; //下一审核人
    private Integer deptId; //销售计划制定者部门编号
    private List<Integer> deptidList;
    private double maxRealMoney; //当月最高实际销售额
    private double minRealMoney; //当月最低实际销售额
    private String checkGrade; //考核评级

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public List<Integer> getDeptidList() {
        return deptidList;
    }

    public void setDeptidList(List<Integer> deptidList) {
        this.deptidList = deptidList;
    }

    public double getMaxRealMoney() {
        return maxRealMoney;
    }

    public void setMaxRealMoney(double maxRealMoney) {
        this.maxRealMoney = maxRealMoney;
    }

    public double getMinRealMoney() {
        return minRealMoney;
    }

    public void setMinRealMoney(double minRealMoney) {
        this.minRealMoney = minRealMoney;
    }

    public String getCheckGrade() {
        return checkGrade;
    }

    public void setCheckGrade(String checkGrade) {
        this.checkGrade = checkGrade;
    }
}
