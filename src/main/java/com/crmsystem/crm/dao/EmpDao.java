package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-04 19:27
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
public interface EmpDao {
    //查询所有在职的销售代表
    List<Emp> findAllEmp(@Param("states") String states,
                         @Param("rolesId") Integer rolesId);
    //过滤查询同事总人数
    int findColleagueCount(@Param("empCode") String empCode,
                           @Param("states") String states,
                           @Param("deptId") Integer deptId);
    //分页过滤查询自己的同事
    List<Emp> findColleaguePage(@Param("empCode") String empCode,
                            @Param("states") String states,
                            @Param("deptId") Integer deptId,
                            @Param("pageIndex")Integer pageIndex,
                                @Param("pageSize")Integer pageSize);

    //根据部门编号查看不是经理的销售代表
    List<String> findEmpByDeptAndRolesId(@Param("deptId") Integer deptId,
                                 @Param("rolesId") Integer rolesId);

    //员工登录的方法
    Emp login(@Param("empCode") String empCode, @Param("password") String password);

    //根据员工编号获取员工
    Emp findEmpByCode(@Param("empCode") String empCode);

    //获取员工上级的方法
    Emp findEmpSuperior(@Param("rolesId")Integer rolesId,@Param("deptId")Integer deptId);

    //分页条件检索员工
    List<Emp> findEmpPaging(@Param("emp")Emp emp,
                            @Param("pageIndex")Integer pageIndex,
                            @Param("pageSize")Integer pageSize);

    //分页条件检索统计数量
    int findEmpCot(@Param("emp")Emp emp);

    //查找员工状态的方法
    List<String> findDeptStates();

}
