package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Commonthly;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:32
 * @version:第1版
 * @description:公司月报表Service接口
 **/
public interface CommonthlyService {
    //修改公司月报
    int updateCommonthly(Commonthly commonthly);
    //创建公司月报
    int addCommonthly(Commonthly commonthly);
    //根据当前时间查看当月的公司月报
    Commonthly findCommonthlyByYM(Commonthly commonthly);
}
