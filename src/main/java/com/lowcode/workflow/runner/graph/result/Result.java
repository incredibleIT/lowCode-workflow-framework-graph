package com.lowcode.workflow.runner.graph.result;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 通用结果集返回, 支持静态工厂, 链式调用
 * @param <T> 数据类型
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    private int code;

    private String msg;

    private T data;


    protected Result() {}
    protected Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    // ————————————————————静态工厂————————————————————

    // 成功
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(200, msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    // 失败
    public static <T> Result<T> error() {
        return new Result<>(500, "系统错误", null);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(500, msg, data);
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> error(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    // ————————————————————支持链式调用————————————————————

    public Result<T> code(int code) {
        this.code = code;
        return this;
    }

    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }
}
