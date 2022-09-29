package com.order.domain;

/**
 * @ClassName Result
 * @Description
 * @Author 徐仡
 * @Date 2022/9/29 16:15
 * @Version 1.0
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }
}
