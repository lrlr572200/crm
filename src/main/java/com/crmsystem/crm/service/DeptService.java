package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:34
 * @version:第1版
 * @description:部门表Service接口
 **/
public interface DeptService {

    //查找直属下属部门根据父类id
    List<Dept> findDeptByParentid(int deptId);

    //条件查询部门
    List<Dept> findDept();
}
