package com.wong.controller;

import com.wong.dao.mysql.service.WordService;
import com.wong.utils.Log;
import com.wong.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userinsert")
public class InsertController {

    @Autowired
    WordService wordService;

    /**
     * 一个简单的功能，就是将客户端发送过来的单词id存进数据库当中
     * @param words
     * @return
     */
    @PostMapping("/rember")
    public Result insermember(@RequestHeader("Authorization")String token, @RequestBody List<String> words){
        Log.i("InsertController：向rememberWords进行插入操作。");
        return wordService.insertRemember(token,words);
    }


   @PostMapping("/forget")
    public Result insertforget(@RequestHeader("Authorization")String token,@RequestBody List<String> words){
        Log.i("InsertController:向forgetWotds进行插入操作。");
        return wordService.insertForget(token,words);
   }
}
