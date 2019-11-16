package com.crmsystem.crm.entity;

import java.io.Serializable;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-04 11:32
 * @version:第1版
 * @description:权限表持久化类
 **/


public class Right implements Serializable {

    private Integer rightId; //权限id
    private Integer rolesId; //角色
    private Integer grade; //权限等级
    private String main; //权限内容


    public long getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }


    public long getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }


    public long getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }


    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

}
