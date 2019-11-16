package com.crmsystem.crm.util;

import java.util.Date;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-14 17:14
 * @version:第1版
 * @description:部门月报工具类
 **/
public class CommonthlyUtil {
    private Integer deptId;//部门ID
    private String deptName; //部门名称
    private String space;//  ""    空串
    private Date starTime;//开始时间
    private Date endTime;//结束时间

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
