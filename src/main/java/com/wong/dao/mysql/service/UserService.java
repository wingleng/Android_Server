package com.wong.dao.mysql.service;

import com.wong.dao.mysql.pojo.entity.User;
import com.wong.vo.Result;

public interface UserService {
    /**
     * 新增用户
     */
    Result AddNewUser(String userName, String userPassword);

    /**
     * 查询用户名密码
     * @param username
     * @param password
     * @return
     */
    User findUser(String username, String password);

    /**
     * 查询用户名是否有重复的
     */
    boolean isRepeatedUserName(String username);

    Result getUserInfoByToken(String token);
}
