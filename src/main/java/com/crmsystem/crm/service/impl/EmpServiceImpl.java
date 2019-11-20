package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.EmpDao;
import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.service.EmpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-04 19:32
 * @version:第1版
 * @description:此处添加对该类的说明
 **/
@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpDao empDao;
    //查看所有在职的员工
    @Override
    public List<Emp> findAllEmp(String states,Integer rolesId) {
        return empDao.findAllEmp(states,rolesId);
    }

    //过滤查看同事总人数
    @Override
    public int findColleagueCount(String empCode, String states, Integer deptId) {
        return empDao.findColleagueCount(empCode,states,deptId);
    }

    //分页过滤查询自己的同事
    @Override
    public List<Emp> findColleaguePage(String empCode, String states,Integer deptId,Integer pageIndex,Integer pageSize)
    {
        return empDao.findColleaguePage(empCode,states,deptId,pageIndex,pageSize);
    }

    //根据部门编号查看不是经理的销售代表
    @Override
    public List<String> findEmpByDeptAndRolesId(Integer deptId, Integer rolesId,String states) {
        return empDao.findEmpByDeptAndRolesId(deptId,rolesId,states);
    }

    //员工登录的方法
    @Override
    public Emp login(String empCode, String password) {
        Emp emp = empDao.login(empCode, password);
        return emp;
    }

    //根据员工编号获取员工的方法
    @Override
    public Emp findEmpByCode(String empCode) {
        return empDao.findEmpByCode(empCode);
    }

    //获取员工上级的方法
    @Override
    public Emp findEmpSuperior(Integer rolesId, Integer deptId) {
        return empDao.findEmpSuperior(rolesId,deptId);
    }

    //分页条件检索员工
    @Override
    public List<Emp> findEmpPaging(Emp emp, Integer pageIndex, Integer pageSize) {
        return empDao.findEmpPaging(emp,pageIndex,pageSize);
    }

    //分页条件检索统计数量
    @Override
    public int findEmpCot(Emp emp) {
        return empDao.findEmpCot(emp);
    }

    //查找员工状态的方法
    @Override
    public List<String> findDeptStates() {
        return empDao.findDeptStates();
    }

    //查看本部门非离职的销售代表的信息
    @Override
    public List<Emp> findEmpInfo(Emp emp) {
        return empDao.findEmpInfo(emp);
    }

    //修改员工的方法
    @Override
    @Transactional
    public int updEmpById(Emp emp) {
        return empDao.updEmpById(emp);
    }

    //删除员工
    @Override
    @Transactional
    public int delEmpById(String empCode) {
        return empDao.delEmpById(empCode);
    }

    //添加员工
    @Override
    @Transactional
    public int addEmp(Emp emp) {
        return empDao.addEmp(emp);
    }
}
