package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Plan;
import com.crmsystem.crm.util.PlanCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:52
 * @version:第1版
 * @description:销售计划表Service接口
 **/
public interface PlanService {
    //查看销售代表或经理被打回的计划数量
    int findCountByStates(Plan plan);

    //销售计划分页检索查询
    List<Plan> findPlanPaging(PlanCondition planCondition, int pageIndex, int pageSize);
    //销售计划分页检索统计数量
    int planCount(PlanCondition planCondition);

    //根据条件分页查询审核计划
    List<Plan> findAllPlan(String empCode, int pageIndex, int pageSize);
    //根据条件查询计划数量
    int findChanCountByCode(String empCode);

    //添加销售计划
    int addPlan(Plan plan);

    //修改审核计划状态
    int updatePlan(Integer planId, String states);


    //修改销售计划
    int updPlan(Plan plan);

    //删除销售计划
    int delPlanById(Integer planId);

    //根据编号查找销售计划
    Plan findPlanById(Integer planId);

    //动态查找销售计划
    List<Plan> findDynPlan(PlanCondition planCondition);

}
