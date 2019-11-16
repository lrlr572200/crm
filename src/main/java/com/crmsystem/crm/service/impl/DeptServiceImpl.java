package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.DeptDao;
import com.crmsystem.crm.entity.Dept;
import com.crmsystem.crm.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:36
 * @version:第1版
 * @description:部门表Service接口实现类
 **/
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptDao deptDao;

    //查找直属下属部门根据父类id
    @Override
    public List<Dept> findDeptByParentid(int deptId) {
        return deptDao.findDeptByParentid(deptId);
    }

    //条件查询部门
    @Override
    public List<Dept> findDept() {
        return deptDao.findDept();
    }
}
