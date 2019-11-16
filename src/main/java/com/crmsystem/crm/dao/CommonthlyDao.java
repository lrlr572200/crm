package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Commonthly;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:18
 * @version:第1版
 * @description:公司月报表Dao接口
 **/
public interface CommonthlyDao {
    //修改公司月报
    int updateCommonthly(Commonthly commonthly);
    //创建公司月报
    int addCommonthly(Commonthly commonthly);
    //根据当前时间查看当月的公司月报
    Commonthly findCommonthlyByYM(Commonthly commonthly);
}
