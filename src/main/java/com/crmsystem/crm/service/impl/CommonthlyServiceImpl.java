package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.CommonthlyDao;
import com.crmsystem.crm.entity.Commonthly;
import com.crmsystem.crm.service.CommonthlyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:33
 * @version:第1版
 * @description:公司月报表Service接口实现类
 **/
@Service
public class CommonthlyServiceImpl implements CommonthlyService {
    @Resource
    CommonthlyDao commonthlyDao;
    //修改公司月报
    @Override
    public int updateCommonthly(Commonthly commonthly) {
        return commonthlyDao.updateCommonthly(commonthly);
    }

    //创建公司月报
    @Override
    public int addCommonthly(Commonthly commonthly) {
        return commonthlyDao.addCommonthly(commonthly);
    }

    //根据当前时间查看当月的公司月报
    @Override
    public Commonthly findCommonthlyByYM(Commonthly commonthly) {
        return commonthlyDao.findCommonthlyByYM(commonthly);
    }
}
