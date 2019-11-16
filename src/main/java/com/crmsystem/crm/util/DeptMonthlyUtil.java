package com.crmsystem.crm.util;

import java.util.Date;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-13 11:17
 * @version:第1版
 * @description:根据角色名称查看 合同编号不为空时的总销售额的工具类
 **/
public class DeptMonthlyUtil {
    private String empName;//销售代表姓名
    private String space;//  ""    空串
    private Date starTime;//开始时间
    private Date endTime;//结束时间

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public Date getStarTime() {
        return starTime;
    }

    public void setStarTime(Date starTime) {
        this.starTime = starTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
