package com.example.wx.system.rest;

/**
 * @description: 返回结果状态码
 * @author:
 * @time:
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),

    FAIL(500, "操作失败"),

    FORBID(403, "无权访问"),

    OFFLINE(300, "无权访问");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultCode getResultCode(int code) {
        ResultCode[] values = ResultCode.values();
        for (ResultCode res : values) {
            if (res.getCode() == code) {
                return res;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
