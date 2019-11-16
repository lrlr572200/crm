package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.NoticeDao;
import com.crmsystem.crm.entity.Notice;
import com.crmsystem.crm.service.NoticeService;
import com.crmsystem.crm.util.NoticeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:49
 * @version:第1版
 * @description:公告表Service接口实现类
 **/
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    NoticeDao noticeDao=null;
    //查询一个公告
    @Override
    public NoticeUtil findANotice(Integer noticeId) {
        NoticeUtil notice = noticeDao.findANotice(noticeId);
        return notice;
    }

    //查询所有公告
    @Override
    public List<NoticeUtil> findAllNotice() {
        List<NoticeUtil> noticeList = noticeDao.findAllNotice();
        return noticeList;
    }
    //添加公告
    @Override
    public int addNotice(Notice notice) {

        return noticeDao.addNotice(notice);
    }
    //删除公告
    @Override
    public int delNotice(Integer noticeId) {
        return noticeDao.delNotice(noticeId);
    }
    //修改公告
    @Override
    public int updateNotice(Notice notice) {
        return noticeDao.updateNotice(notice);
    }


    //分页过滤查询公告
    @Override
    public List<NoticeUtil> findNoticePage(Integer pageSize, Integer pageIndex, NoticeUtil notice) {
        List<NoticeUtil> noticeList = noticeDao.findNoticePage(pageSize, pageIndex, notice);
        return noticeList;
    }
    //查询公告总数量
    @Override
    public int findNoticeCount(NoticeUtil notice) {
        int totalCount = noticeDao.findNoticeCount(notice);
        return totalCount;
    }
}
