package com.crmsystem.crm.util;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: 刘仁
 * @create: 2019-11-12 11:37
 * @version:第1版
 * @description:查看客户消费次数和消费总金额的工具类
 **/

public class CmSum {
    private Integer times;//总消费次数
    private Double amount;//总消费金额

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
