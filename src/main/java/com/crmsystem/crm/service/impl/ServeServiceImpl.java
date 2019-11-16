package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.ServeDao;
import com.crmsystem.crm.entity.Serve;
import com.crmsystem.crm.service.ServeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 15:00
 * @version:第1版
 * @description:服务表Service接口实现类
 **/
@Service
public class ServeServiceImpl implements ServeService {
    @Resource
    ServeDao serveDao;
    //创建服务
    @Override
    public int addServe(Serve serve) {
        return serveDao.addServe(serve);
    }

    //分页过滤查看服务
    @Override
    public List<Serve> findServePage(Integer pageIndex, Integer pageSize, Serve serve) {
        return serveDao.findServePage(pageIndex,pageSize,serve);
    }
    //过滤查看服务总数量
    @Override
    public int findServeCount(Serve serve) {
        return serveDao.findServeCount(serve);
    }




    //分页检索的方法
    @Override
    public List<Serve> findServePaging(Serve serve, Integer pageIndex, Integer pageSize) {
        return serveDao.findServePaging(serve,pageIndex,pageSize);
    }

    //分页检索统计数据量
    @Override
    public int fintServeCot(Serve serve) {
        return serveDao.fintServeCot(serve);
    }

    //修改服务单的方法
    @Override
    public int updServe(Serve serve) {
        return serveDao.updServe(serve);
    }
}
