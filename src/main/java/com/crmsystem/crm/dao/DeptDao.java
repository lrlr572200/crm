package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:19
 * @version:第1版
 * @description:部门表Dao接口
 **/
public interface DeptDao {
    //查找直属下属部门根据父类id
    List<Dept> findDeptByParentid(@Param("deptId") int deptId);

    //条件查询部门
    List<Dept> findDept();

    //动态查找部门
    List<Dept> findDynDept(@Param("dept") Dept dept);

}
