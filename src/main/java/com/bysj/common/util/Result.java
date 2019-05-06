package com.bysj.common.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口响应信息
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    private T Data;

    private Integer ErrorCode;

    private String ErrorMessage;

    public static Result OK = new Result(SUCCESS,"操作成功"); //请求成功,,没啥好回复给前端

    public static Result ERROR = new Result(FAIL,"系统异常");  //请求失败,,失败

    public static Result NOT_FOUND = new Result(FAIL, "资源不存在");

    public Result(){}

    public Result(T data,Integer ErrorCode,String ErrorMessage){
        this.Data = data;
        this.ErrorCode = ErrorCode;
        this.ErrorMessage = ErrorMessage;
    }

    public Result(T data,Integer ErrorCode){
        this.Data = data;
        this.ErrorCode = ErrorCode;
    }

    public Result(Integer ErrorCode,String ErrorMessage){
        this.ErrorCode = ErrorCode;
        this.ErrorMessage = ErrorMessage;
    }

    public Result(Integer ErrorCode){
        this.ErrorCode = ErrorCode;
    }
}