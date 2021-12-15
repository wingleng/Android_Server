package com.wong.dao.mysql.service;

import com.wong.vo.Result;

import java.util.List;

public interface WordService {

    /**
     * 对RememberList进行操作
     * @param idlist
     * @return
     */
    Result insertRemember(String token,List<String> idlist);
}
