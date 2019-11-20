package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Plan;
import com.crmsystem.crm.util.PlanCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:26
 * @version:第1版
 * @description:销售计划表Dao接口
 **/
public interface PlanDao {
    //销售计划分页检索查询
    List<Plan> findPlanPaging(@Param("planCondition") PlanCondition planCondition,
                              @Param("pageIndex") int pageIndex,
                              @Param("pageSize") int pageSize);

    //销售计划分页检索统计数量
    int planCount(@Param("planCondition") PlanCondition planCondition);

    //根据条件分页查询审核计划
    List<Plan> findAllPlan(@Param("empCode") String empCode,
                           @Param("pageIndex") int pageIndex,
                           @Param("pageSize") int pageSize);
    //根据条件查询员工数量数量
    int findChanCountByCode(@Param("empCode") String empCode);

    //添加销售计划
    int addPlan(Plan plan);

    //修改审核计划状态
    int updatePlan(@Param("planId") Integer planId, @Param("states") String states);

    //修改销售计划
    int updPlan(Plan plan);

    //删除销售计划
    int delPlanById(@Param("planId") Integer planId);

    //根据编号查找销售计划
    Plan findPlanById(@Param("planId") Integer planId);

}
