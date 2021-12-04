package com.wong.dao.mongo.mapper;

import com.wong.dao.mongo.pojo.Word;

import java.util.List;

public interface QueryMapper {
    /**
     * 直接随机查询指定个单词返回
     */
    public List<Word> randomWords(int numbers,Class<Word> cls);

    /**
     * 查询单词总量
     */
    public int wordNum();
}
