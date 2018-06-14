package com.accp.entity;


import java.util.Date;

/***
 *用户表
 */
public class User {
    public User() {
        super();
    }

    public User(int id, String userState) {
        this.id = id;
        this.userState = userState;
    }

    public User(String userName, String userPwd, String userState) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userState = userState;
    }

    private int id;//用户编号
    private String userName;//用户名
    private String userPwd;//用户密码
    private String userRole; //用户角色
    private String userSex; //性别
    private Date userTime; //创建日期
    private String userState; //用户状态(已启用,已停用)
    private String name;//用户姓名

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
