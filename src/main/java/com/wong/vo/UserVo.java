package com.wong.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回给客户端使用的用户信息，其实就是去掉了密码。这个用户设计的太简单了，感觉这个vo没什么必要
 */
@Data
public class UserVo {

    private Integer userId;

    private String userName;
}
