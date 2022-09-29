package com.order.domain;

/**
 * @ClassName User
 * @Description
 * @Author 徐仡
 * @Date 2022/9/29 17:03
 * @Version 1.0
 */
public class User {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
