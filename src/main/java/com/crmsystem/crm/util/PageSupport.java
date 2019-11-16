package com.crmsystem.crm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-04 11:32
 * @version:第1版
 * @description:分页工具类
 **/
public class PageSupport<T> {

    private int pageIndex;//页码
    private int pageSize;//页大小
    private int pageCount;//总页数
    private int totalCount;//总记录数
    private List<T> dataList=new ArrayList<T>();//页内容

    public PageSupport() {
    }

    public PageSupport(int pageIndex, int pageSize, int totalCount, List<T> dataList) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.dataList = dataList;
    }

    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageCount() {
        return totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1 ;
    }

    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public List<T> getDataList() {
        return dataList;
    }
    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
