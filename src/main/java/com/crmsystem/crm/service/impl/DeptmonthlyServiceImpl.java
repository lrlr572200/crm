package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.DeptmonthlyDao;
import com.crmsystem.crm.entity.Deptmonthly;
import com.crmsystem.crm.service.DeptmonthlyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:38
 * @version:第1版
 * @description:部门月报表Service接口类实现类
 **/
@Service
public class DeptmonthlyServiceImpl implements DeptmonthlyService {
    @Resource
    DeptmonthlyDao deptmonthlyDao;
    //修改当前月报表
    @Override
    public int updateDeptMonthly(Deptmonthly monthly) {
        return deptmonthlyDao.updateDeptMonthly(monthly);
    }

    //创建部门月报表
    @Override
    public int addDeptMonthly(Deptmonthly monthly) {
        return deptmonthlyDao.addDeptMonthly(monthly);
    }

    //获取当前月份的部门报表
    @Override
    public Deptmonthly findDeptMonthlyByYM(Deptmonthly monthly) {
        return deptmonthlyDao.findDeptMonthlyByYM(monthly);
    }

    //统计部门月报
    @Override
    public long deptSaleSum(Integer year, Integer month, Integer deptId) {
        return deptmonthlyDao.deptSaleSum(year,month,deptId);
    }
}
