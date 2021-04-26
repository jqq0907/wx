package com.example.wx.system.rest;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 返回结果
 * @author:
 * @time:
 */
@ApiModel(value = "result", description = "返回封装类")
@Data
@AllArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private int code;

    /****
     * 状态
     */
    @ApiModelProperty("状态")
    private boolean status;

    /**
     * 消息
     */
    @ApiModelProperty("提示消息")
    private String message;

    /**
     * 数据
     */
    @ApiModelProperty("返回数据")
    private T data;

    /**
     * 登录标识 0 ：需要登录 1： 登录成功
     */
    @ApiModelProperty("登录标志")
    private String loginSuccess;


    /**
     * 操作结果返回值
     *
     * @param flag
     * @param <T>
     * @return
     */
    public static <T> Result result(boolean flag) {
        if (flag) {
            return success(null);
        }
        return fail("");
    }


    /**
     * 数据返回值
     *
     * @param <T>
     * @param msg
     * @return
     */
    public static <T> Result msg(String msg) {
        return result(ResultCode.SUCCESS, true, msg, null);
    }

    public static <T> Result success(T data) {
        return result(ResultCode.SUCCESS, true, null, data);
    }

    public static <T> Result success(T data, String msg) {
        return result(ResultCode.SUCCESS, true, msg, data);
    }

    public static <T> Result fail() {
        return result(ResultCode.FAIL, false, null, null);
    }

    public static <T> Result fail(String msg) {
        return result(ResultCode.FAIL, false, msg, null);
    }

    public static <T> Result fail(T data, String msg) {
        return result(ResultCode.FAIL, false, msg, data);
    }

    public static <T> Result fail(ResultCode code) {
        return result(code, false, null, null);
    }

    public static <T> Result fail(ResultCode code, String msg) {
        return result(code, false, msg, null);
    }

    public static <T> Result result(ResultCode code, boolean status, String message, T data) {
        String msg = code.getMsg();
        if (StrUtil.isNotEmpty(message)) {
            msg = message;
        }
        return new Result<>(code.getCode(), status, msg, data, null);
    }
}
