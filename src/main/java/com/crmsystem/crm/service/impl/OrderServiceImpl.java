package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.OrderDao;
import com.crmsystem.crm.entity.Order;
import com.crmsystem.crm.service.OrderService;

import com.crmsystem.crm.util.CmSum;
import com.crmsystem.crm.util.CommonthlyUtil;
import com.crmsystem.crm.util.DeptMonthlyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:51
 * @version:第1版
 * @description:订单表Service接口实现类
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderDao orderDao;
    //根据部门名查看  合同编号不为空时的销售总额
    @Override
    public Double findSumMoneyAndPactCodeByDeptName(CommonthlyUtil commonthlyUtil) {
        return orderDao.findSumMoneyAndPactCodeByDeptName(commonthlyUtil);
    }

    //根据角色名称查看 合同编号不为空时的总销售额
    @Override
    public Double findSumMoneyAndPactCode(DeptMonthlyUtil deptMonthlyUtil) {
        return orderDao.findSumMoneyAndPactCode(deptMonthlyUtil);
    }

    //删除订单
    @Override
    public int delOrder(Integer orderId) {
        return orderDao.delOrder(orderId);
    }
    //修改订单状态和合同编号
    @Override
    public int updateSPbyOrderId(Integer orderId, String states, String pactCode) {
        return orderDao.updateSPbyOrderId(orderId,states,pactCode);
    }

    //根据ID查看订单信息
    @Override
    public Order findOrderById(Integer orderId) {
        return orderDao.findOrderById(orderId);
    }

    //查询客户消费次数和消费总金额
    @Override
    public CmSum findCountAndMoney(Integer userId, String space) {
        return orderDao.findCountAndMoney(userId,space);
    }


    //添加订单
    @Override
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    //分页过滤查看订单
    @Override
    public List<Order> findOrderPage(Order order, Integer pageIndex, Integer pageSize) {
        return orderDao.findOrderPage(order,pageIndex,pageSize);
    }
    //过滤查看总数量
    @Override
    public int findOrderCount(Order order) {
        return orderDao.findOrderCount(order);
    }
}
