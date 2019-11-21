package com.crmsystem.crm.util;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-20 10:09
 * @version:第1版
 * @description: 角色和权限工具类
 **/
public class RoleRight {

    private Integer rolesId; //角色id
    private String rolesName; //角色名称
    private Integer grade; //权限等级
    private String main; //权限内容

    public Integer getRolesId() {
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

    public Integer getGrade() {
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
