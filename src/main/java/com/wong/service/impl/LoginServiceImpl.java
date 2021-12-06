package com.wong.service.impl;

import com.alibaba.fastjson.JSON;
import com.wong.dao.mysql.pojo.entity.User;
import com.wong.dao.mysql.service.UserService;
import com.wong.service.LoginService;
import com.wong.utils.JWTUtils;
import com.wong.utils.SomeProperties;
import com.wong.vo.ErrorCode;
import com.wong.vo.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    /**
     * 1. 判断数据合法性
     * 2. 前往数据库中进行查找
     * 3. 生成token
     * 4. （待确定）使用Redis保存token。。
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result login(String username, String password) {

        //判空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        //前往数据库中进行查询
        password = DigestUtils.md5Hex(password + SomeProperties.salt);
        User user = userService.findUser(username,password);

        //查询结果判断
        if(user == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        //创建token，并且放进redis中？
        //TODO:这里可能会出现问题。
        String token = JWTUtils.createToken(Long.valueOf(user.getUserId()));
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);

        //返回token
        return Result.success(token);
    }

    /**
     * 使用token来获取当前用户
     * 1. 判断token是否有效
     * 2. 从redis中获取用户对象
     * 3. 返回用户对象
     */
    @Override
    public User checkToken(String token) {
        //首先判断这个token是否为空：
        if(StringUtils.isBlank(token)){
            return null;
        }
        //2.进行验证，一些什么验证之类的，但是这里没必要，能够证明
        Map<String,Object> stringObjectMap = JWTUtils.checkToken(token);
        if(stringObjectMap == null){
            return null;
        }
        //3.最后到redis中获取对象
        String userJson = redisTemplate.opsForValue().get("TOKEN_"+token);
        if(StringUtils.isBlank(userJson)){
            return null;
        }
        User sysUser = JSON.parseObject(userJson,User.class);
        return sysUser;
    }

}
