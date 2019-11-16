package com.crmsystem.crm.entity;

import java.io.Serializable;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-04 11:32
 * @version:第1版
 * @description:部门月报表持久化类
 **/

public class Deptmonthly implements Serializable {

    private Integer id; //部门月报id
    private String year; //年份
    private String month; //月份
    private double sumMoney; //月总销售额
    private Integer deptId; //部门编号
    private String deptName; //部门名称   工具属性，数据库无此字段
    private String basis; //同比
    private String ratio; //环比

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}
