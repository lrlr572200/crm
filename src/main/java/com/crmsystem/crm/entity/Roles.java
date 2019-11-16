package com.crmsystem.crm.entity;

import java.io.Serializable;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-04 11:32
 * @version:第1版
 * @description:角色表持久化类
 **/

public class Roles implements Serializable {

    private Integer rolesId; //角色id
    private String rolesName; //角色名称


    public long getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }


    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

}
