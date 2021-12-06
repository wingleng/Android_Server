package com.wong.service;

import com.wong.dao.mysql.pojo.entity.User;
import com.wong.vo.Result;

public interface LoginService {

    /**
     * 验证用户登录并发送token的方法
     */
    Result login(String username,String password);

    /**
     * 使用token获取User对象
     */
    User checkToken(String token);
}
