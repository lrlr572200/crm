package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Deptmonthly;
import org.apache.ibatis.annotations.Param;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:20
 * @version:第1版
 * @description:部门月报表Dao接口
 **/
public interface DeptmonthlyDao {
    //修改当前月报表
    int updateDeptMonthly(Deptmonthly monthly);
    //获取当前月份的部门报表
    Deptmonthly findDeptMonthlyByYM(Deptmonthly monthly);
    //创建部门月报表
    int addDeptMonthly(Deptmonthly monthly);
}
