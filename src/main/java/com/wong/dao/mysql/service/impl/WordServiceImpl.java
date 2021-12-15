package com.wong.dao.mysql.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wong.dao.mysql.mapper.ForgetWordMapper;
import com.wong.dao.mysql.mapper.RememberedWordMapper;
import com.wong.dao.mysql.pojo.entity.ForgetWord;
import com.wong.dao.mysql.pojo.entity.RememberedWord;
import com.wong.dao.mysql.pojo.entity.User;
import com.wong.dao.mysql.service.WordService;
import com.wong.service.LoginService;
import com.wong.vo.ErrorCode;
import com.wong.vo.Result;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    RememberedWordMapper rememberedWordMapper;

    @Autowired
    ForgetWordMapper forgetWordMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    LoginService loginService;

    /**
     * 该方法用来添加到rememberword表中
     * @param token
     * @param idlist
     * @return
     */
    @Override
    public Result insertRemember(String token,List<String> idlist) {
        //从Redis中取出用户，但是感觉token为空的情况下就悲剧了。。
        User user = loginService.checkToken(token);
        //进行插入
        Integer usrid = user.getUserId();
        int nums = 0;
        for (String wordId : idlist) {
           nums += rememberedWordMapper.insert(new RememberedWord(usrid,wordId,new Date()));
        }
        if (nums==idlist.size()-1)
            return Result.success("Remember插入了"+nums+"条数据");
        return Result.fail(ErrorCode.INSERT_ERROR.getCode(), ErrorCode.INSERT_ERROR.getMsg());
    }


    /**
     * 对ForgetWord表进行插入操作。
     * @param token
     * @param idlist
     * @return
     */
    @Override
    public Result insertForget(String token,List<String> idlist) {
        //从Redis中取出用户，但是感觉token为空的情况下就悲剧了。。
        User user = loginService.checkToken(token);
        //进行插入
        Integer usrid = user.getUserId();
        int nums = 0;
        for (String wordId : idlist) {
            nums += forgetWordMapper.insert(new ForgetWord(usrid,wordId,new Date()));
        }
        if (nums==idlist.size()-1)
            return Result.success("Forget插入了"+nums+"条数据");
        return Result.fail(ErrorCode.INSERT_ERROR.getCode(), ErrorCode.INSERT_ERROR.getMsg());
    }
}
