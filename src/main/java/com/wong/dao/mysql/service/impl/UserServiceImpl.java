package com.wong.dao.mysql.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wong.dao.mysql.mapper.UserMapper;
import com.wong.dao.mysql.pojo.entity.User;
import com.wong.dao.mysql.service.UserService;
import com.wong.utils.JWTUtils;
import com.wong.utils.Log;
import com.wong.utils.SomeProperties;
import com.wong.vo.ErrorCode;
import com.wong.vo.Result;
import com.wong.vo.UserVo;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
//    private final static String salt = "wong";//配置一个加密盐

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;
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
        //先判断空
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(userPassword)){
            Log.i("用户名或密码为空");
            return Result.fail(ErrorCode.NULL_PARAM.getCode(), ErrorCode.NULL_PARAM.getMsg());
        }

        //先查重
        if (isRepeatedUserName(userName)){
            Log.i("用户名重复！！！");
            return Result.fail(ErrorCode.ACCOUNT_EXITS.getCode(), ErrorCode.ACCOUNT_EXITS.getMsg());
        }

        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(DigestUtils.md5Hex(userPassword + SomeProperties.salt));
        int result = userMapper.insert(user);
        Log.i("成功插入一条数据");
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

    /**
     * 直接通过token获取当前用户信息。
     * @param token
     * @return
     */
    @Override
    public Result getUserInfoByToken(String token) {
        Map<String,Object> map = JWTUtils.checkToken(token);
        if(map == null){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if(StringUtils.isBlank(userJson)){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
        }
        User user = JSON.parseObject(userJson,User.class);
        UserVo userVo = new UserVo();
        userVo.setUserId(user.getUserId());
        userVo.setUserName(user.getUserName());

        return Result.success(userVo);
    }
}
