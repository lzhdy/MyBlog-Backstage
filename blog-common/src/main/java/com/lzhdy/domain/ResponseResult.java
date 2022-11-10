package com.lzhdy.domain;

import com.lzhdy.enums.ResponseCodeEnum;

import java.io.Serializable;

/**
 * @author lzhdy
 * @date 2022/11/7
 * @apiNote 统一响应类
 */

public class ResponseResult<T> implements Serializable {

    // 响应状态码
    private Integer code;
    // 响应信息
    private String msg;
    // 响应内容
    private  T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 失败
    public ResponseResult<T> error(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    // 成功
    public ResponseResult<T> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }
    // 通用失败无数据返回, (500, 错误)
    public static ResponseResult<?> errorResult() {
        return new ResponseResult<>(ResponseCodeEnum.ERROR.getCode(),ResponseCodeEnum.ERROR.getMsg());
    }

    // 通用成功无数据返回, (200, 成功)
    public static ResponseResult<?> okResult() {
        return new ResponseResult<>(ResponseCodeEnum.SUCCESS.getCode(),ResponseCodeEnum.SUCCESS.getMsg());
    }

    // 成功返回数据
    public static <T> ResponseResult<T> okResult(T data) {
        ResponseResult<T> result = setAppHttpCodeEnum(ResponseCodeEnum.SUCCESS);
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    // 自定义设置响应码和信息, 无数据返回
    private static <T> ResponseResult<T> setAppHttpCodeEnum(ResponseCodeEnum enums){
        return new ResponseResult<>(enums.getCode(), enums.getMsg());
    }

}
