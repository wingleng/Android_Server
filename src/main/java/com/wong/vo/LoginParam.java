package com.wong.vo;

import lombok.Data;

/**
 * 这个类是用户登录时的账号密码的json类的实体类
 */
@Data
public class LoginParam {
    final String username;
    final String password;

    public LoginParam(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
