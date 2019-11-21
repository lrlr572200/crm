package com.crmsystem.crm.entity;

import java.io.Serializable;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-04 11:32
 * @version:第1版
 * @description:系统变量表持久化类
 **/

public class System implements Serializable {

    private Integer id; //变量id
    private String name; //变量名
    private String value; //变量值
    private String declare; //变量说明


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDeclare() {
        return declare;
    }

    public void setDeclare(String declare) {
        this.declare = declare;
    }
}
