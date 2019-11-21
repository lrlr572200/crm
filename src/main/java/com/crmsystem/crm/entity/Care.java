package com.crmsystem.crm.entity;
/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-11-04 11:32
 * @version:第1版
 * @description:客户关怀表持久化类
 **/

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Care implements Serializable {

    private Integer careId; //关怀id，主键
    private Integer userId; //客户id，外键
    private String userName;//客户姓名   （工具属性，数据库无此字段）
    private String main; //关怀内容
    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date careTime; //关怀时间
    private String result; //关怀执行结果
    private String states; //关怀单状态
    private String addCode; //关怀单创建者编号

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCareId() {
        return careId;
    }

    public void setCareId(Integer careId) {
        this.careId = careId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Date getCareTime() {
        return careTime;
    }

    public void setCareTime(Date careTime) {
        this.careTime = careTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getAddCode() {
        return addCode;
    }

    public void setAddCode(String addCode) {
        this.addCode = addCode;
    }
}
