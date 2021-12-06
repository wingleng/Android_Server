package com.wong.dao.mysql.service;

import com.wong.dao.mysql.pojo.entity.User;

public interface UserService {
    /**
     * 新增用户
     */
    public int AddNewUser(String userName,String userPassword);

    /**
     * 查询用户名密码
     * @param username
     * @param password
     * @return
     */
    User findUser(String username, String password);
}
