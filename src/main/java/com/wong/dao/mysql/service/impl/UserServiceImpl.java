package com.wong.dao.mysql.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wong.dao.mysql.mapper.UserMapper;
import com.wong.dao.mysql.pojo.entity.User;
import com.wong.dao.mysql.service.UserService;
import com.wong.utils.Log;
import com.wong.utils.SomeProperties;
import com.wong.vo.ErrorCode;
import com.wong.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
//    private final static String salt = "wong";//配置一个加密盐

    @Autowired
    UserMapper userMapper;

    /**
     * 先查询有没有重复的，再插入啊！
     * @param userName
     * @param userPassword
     * @return
     */
    @Override
    public Result AddNewUser(String userName, String userPassword) {
        Log.i(userName);
        Log.i(userPassword);
        //先查重
        if (isRepeatedUserName(userName)){
            Log.i("用户名重复！！！");
            return Result.fail(ErrorCode.ACCOUNT_EXITS.getCode(), ErrorCode.ACCOUNT_EXITS.getMsg());
        }

        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(DigestUtils.md5Hex(userPassword + SomeProperties.salt));
        int result = userMapper.insert(user);
        return Result.success("已成功插入1条数据");
    }

    /**
     * 验证用户名密码是否正确
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUser(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        queryWrapper.eq(User::getUserPassword,password);
        queryWrapper.select(User::getUserId,User::getUserName,User::getUserPassword);
        queryWrapper.last("limit 1");

        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean isRepeatedUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        queryWrapper.select(User::getUserId,User::getUserName,User::getUserPassword);
        queryWrapper.last("limit 1");
        User user = userMapper.selectOne(queryWrapper);
        Log.i("查重"+user);
        return user==null?false:true;
    }
}
