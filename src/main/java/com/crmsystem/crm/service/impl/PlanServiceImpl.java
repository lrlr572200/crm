package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.PlanDao;
import com.crmsystem.crm.entity.Plan;
import com.crmsystem.crm.service.PlanService;
import com.crmsystem.crm.util.PlanCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:53
 * @version:第1版
 * @description:销售计划表Service接口实现类
 **/
@Service
public class PlanServiceImpl implements PlanService {
    @Resource
    private PlanDao planDao;

    //根据条件分页查询审核计划
    @Override
    public List<Plan> findAllPlan(String empCode, int pageIndex, int pageSize) {
        return planDao.findAllPlan(empCode,pageIndex,pageSize);
    }

    @Override
    public int findChanCountByCode(String empCode) {
        return planDao.findChanCountByCode(empCode);
    }
    //查看销售代表或经理被打回的计划数量
    @Override
    public int findCountByStates(Plan plan) {
        return planDao.findCountByStates(plan);
    }

    //销售计划分页检索查询
    @Override
    public List<Plan> findPlanPaging(PlanCondition planCondition, int pageIndex, int pageSize) {
        return planDao.findPlanPaging(planCondition,pageIndex,pageSize);
    }

    //销售计划分页检索统计数量
    @Override
    public int planCount(PlanCondition planCondition) {
        return planDao.planCount(planCondition);
    }

    //添加销售计划
    @Transactional
    @Override
    public int addPlan(Plan plan) {
        return planDao.addPlan(plan);
    }


    @Override
    public int updatePlan(Integer planId, String states) {
        return planDao.updatePlan(planId,states);
    }

    //修改审核计划状态



    //修改销售计划
    @Transactional
    @Override
    public int updPlan(Plan plan) {
        return planDao.updPlan(plan);
    }

    //删除销售计划
    @Transactional
    @Override
    public int delPlanById(Integer planId) {
        return planDao.delPlanById(planId);
    }

    //根据编号查找销售计划
    @Override
    public Plan findPlanById(Integer planId) {
        return planDao.findPlanById(planId);
    }

    //动态查找销售计划
    @Override
    public List<Plan> findDynPlan(PlanCondition planCondition) {
        return planDao.findDynPlan(planCondition);
    }
}
