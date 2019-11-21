package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.CareDao;
import com.crmsystem.crm.entity.Care;
import com.crmsystem.crm.service.CareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:16
 * @version:第1版
 * @description:客户关怀表Service接口实现类
 **/
@Service
public class CareServiceImpl implements CareService {
    @Resource
    CareDao careDao;
    //查看未过期的关怀计划
    @Override
    public List<Care> findCareByNow(String empCode) {
        return careDao.findCareByNow(empCode);
    }

    //修改客户关怀计划
    @Override
    public int updateCareById(Care care) {
        return careDao.updateCareById(care);
    }

    //根据客户ID查看关怀计划
    @Override
    public Care findCareById(Care care) {
        return careDao.findCareById(care);
    }

    //删除关怀计划
    @Override
    public int delCare(Care care) {
        return careDao.delCare(care);
    }

    //添加关怀计划
    @Override
    public int addCare(Care care) {
        return careDao.addCare(care);
    }

    //过滤查看客户关怀表的总数量
    @Override
    public int findCareCountByUserId(Care care) {
        return careDao.findCareCountByUserId(care);
    }

    //分页过滤查看客户的关怀列表
    @Override
    public List<Care> findCareByUserId(Care care, Integer pageIndex, Integer pageSize) {
        return careDao.findCareByUserId(care,pageIndex,pageSize);
    }
}
