package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-04 19:31
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
public interface EmpService {


    //查询所有在职的员工
    List<Emp> findAllEmp(String states,Integer rolesId);

    //过滤查询同事总人数
    int findColleagueCount(String empCode, String states, Integer deptId);

    //查询自己的同事
    List<Emp> findColleaguePage(String empCode, String states,Integer deptId,Integer pageIndex,Integer pageSize);

    //根据部门编号查看不是经理的销售代表
    List<String> findEmpByDeptAndRolesId(Integer deptId, Integer rolesId,String states);

    //员工登录的方法
    Emp login(String empCode, String password);

    //根据编号获取员工
    Emp findEmpByCode(String empCode);

    //获取员工上级的方法
    Emp findEmpSuperior(Integer rolesId, Integer deptId);

    //分页条件检索员工
    List<Emp> findEmpPaging(Emp emp, Integer pageIndex, Integer pageSize);

    //分页条件检索统计数量
    int findEmpCot(Emp emp);

    //查找员工状态的方法
    List<String> findDeptStates();

    //查看本部门非离职的销售代表的信息
    List<Emp> findEmpInfo(Emp emp);

    //修改员工的方法
    int updEmpById(Emp emp);

    //删除员工
    int delEmpById(String empCode);

    //添加员工
    int addEmp(Emp emp);

}
