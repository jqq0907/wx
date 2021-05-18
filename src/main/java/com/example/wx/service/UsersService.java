package com.example.wx.service;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author "
 * @since 2021-05-16
 */
public interface UsersService  {

    /**
     * 微信端登录获取openId
     *
     * @param code code
     * @param userName 微信用户名称
     *
     * @return
     */
    String loginWx(String code, String userName);
}
