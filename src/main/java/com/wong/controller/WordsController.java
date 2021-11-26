package com.wong.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wong.dao.mongo.pojo.Word;
import com.wong.dao.mongo.service.QueryMapperImpl;
import com.wong.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("findwords")
public class WordsController {

    @Autowired
    QueryMapperImpl queryMapper;

    /**
     * 这个接口会根据路径中请求的数量，返回对应的随机单词。
     * @param number
     * @return
     */
    @GetMapping("/randWord/{num}")
    public String randWord(@PathVariable("num") int number){
        List<Word> list=queryMapper.randomWords(number,Word.class);
        String result = JSON.toJSONString(list);
        Log.i("/randWord/{"+number+"},返回的随机单词数："+list.size());
        return result;
    }
}
