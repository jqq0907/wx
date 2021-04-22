package com.example.wx.service;

/**
 * @description:
 * @author:
 * @time:
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 用户密码
     * @return 是否登录成功
     */
    boolean login(String userName, String password);
}
