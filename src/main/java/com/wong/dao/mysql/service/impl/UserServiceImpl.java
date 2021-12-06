package com.wong.dao.mysql.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wong.dao.mysql.mapper.UserMapper;
import com.wong.dao.mysql.pojo.entity.User;
import com.wong.dao.mysql.service.UserService;
import com.wong.utils.Log;
import com.wong.utils.SomeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
//    private final static String salt = "wong";//配置一个加密盐

    @Autowired
    UserMapper userMapper;

    @Override
    public int AddNewUser(String userName, String userPassword) {
        Log.i(userName);
        Log.i(userPassword);
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(DigestUtils.md5Hex(userPassword + SomeProperties.salt));
        int result = userMapper.insert(user);
        return result;
    }

    @Override
    public User findUser(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        queryWrapper.eq(User::getUserPassword,password);
        queryWrapper.select(User::getUserId,User::getUserName,User::getUserPassword);
        queryWrapper.last("limit 1");

        return userMapper.selectOne(queryWrapper);
    }
}
