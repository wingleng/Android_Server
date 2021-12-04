package com.wong.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 原本想使用这个类，将返回接口统一的，但是json转换不熟练，所以还是算了吧。
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