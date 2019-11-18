package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.ChanDao;
import com.crmsystem.crm.entity.Chan;
import com.crmsystem.crm.service.ChanService;
import com.crmsystem.crm.util.PageSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:郝存凯
 * @create: 2019-11-04 14:30
 * @version:第1版
 * @description:机会表Service接口实现类
 **/
@Service
public class ChanServiceImpl implements ChanService {
    @Resource
    ChanDao chanDao;

    @Override
    public int addChan(Chan chan) {
        return chanDao.addChan(chan);
    }

    //查询总数量
    @Override
    public int findChanCount(Chan chan) {
        return chanDao.findChanCount(chan);
    }



    //分页过滤查询机会
    @Override
    public List<Chan> findChanPage(Integer pageIndex, Integer pageSize, Chan chan) {
        return chanDao.findChanPage(pageIndex,pageSize,chan);
    }


    @Override
    public List<Chan> findChanByDid(Integer deptId, Integer pageIndex, Integer pageSize) {
        return chanDao.findChanByDid(deptId,pageIndex,pageSize);
    }


    @Override
    public int findChanCountByDid(Integer deptId) {
        return chanDao.findChanCountByDid(deptId);
    }
    //分页过滤查询的方法--李国庆
    @Override
    public List<Chan> findChanPaging(Integer pageIndex, Integer pageSize, Chan chan) {
        return chanDao.findChanPaging(pageIndex,pageSize,chan);
    }

    //过滤时统计数据量的方法--李国庆
    @Override
    public int findChanCot(Chan chan) {
        return chanDao.findChanCot(chan);
    }

    //查看机会来源
    @Override
    public List<String> findSource() {
        return chanDao.findSource();

    }
    //修改机会分配给员工
    @Override
    public int updateChan(Chan chan) {
        return chanDao.updateChan(chan);
    }
}
