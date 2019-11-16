package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Order;

import com.crmsystem.crm.util.CmSum;
import com.crmsystem.crm.util.CommonthlyUtil;
import com.crmsystem.crm.util.DeptMonthlyUtil;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:25
 * @version:第1版
 * @description:订单表Dao接口
 **/
public interface OrderDao {
    //根据部门名查看  合同编号不为空时的销售总额
    Double findSumMoneyAndPactCodeByDeptName(CommonthlyUtil commonthlyUtil);

    //根据角色名称查看 合同编号不为空时的总销售额
    Double findSumMoneyAndPactCode(DeptMonthlyUtil deptMonthlyUtil);

    //删除订单
    int delOrder(@Param("orderId") Integer orderId);
    //修改订单状态和合同编号
    int updateSPbyOrderId(@Param("orderId") Integer orderId,
                          @Param("states") String states,
                          @Param("pactCode") String pactCode);
    //根据ID查看订单信息
    Order findOrderById(@Param("orderId") Integer orderId);
    //修改订单
    int updateOrder(Order order);
    //根据客户Id查看客户消费次数和消费总金额
    CmSum findCountAndMoney(@Param("userId") Integer userId,
                            @Param("space") String space);
    //添加订单
    int addOrder(Order order);
    //分页过滤查看订单
    List<Order> findOrderPage(@Param("order") Order order,
                              @Param("pageIndex") Integer pageIndex,
                              @Param("pageSize") Integer pageSize);
    //过滤查询订单总数量
    int findOrderCount(@Param("order") Order order);
}
