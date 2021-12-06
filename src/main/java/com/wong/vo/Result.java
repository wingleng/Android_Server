package com.wong.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 使用这个类统一返回接口.可惜安卓端没有使用这个类。。。。
 */
@Data
@AllArgsConstructor
public class Result {

    private boolean success;

    private int code;

    private String msg;

    private Object data;

    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }
    public static Result fail(int code, String msg){
        return new Result(false,code,msg,null);
    }
}