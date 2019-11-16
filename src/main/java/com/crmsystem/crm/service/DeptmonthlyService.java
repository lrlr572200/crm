package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Deptmonthly;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:38
 * @version:第1版
 * @description:部门月报表Service接口类
 **/
public interface DeptmonthlyService {
    //修改当前月报表
    int updateDeptMonthly(Deptmonthly monthly);
    //创建部门月报表
    int addDeptMonthly(Deptmonthly monthly);
    //获取当前月份的部门报表
    Deptmonthly findDeptMonthlyByYM(Deptmonthly monthly);
}
