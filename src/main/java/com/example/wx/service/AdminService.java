package com.example.wx.service;

/**
 * @description: 管理员表 服务类
 * @author:
 * @time:
 */
public interface AdminService {

    /**
     * 登录
     *
     * @param userName 管理员账户
     * @param password 用户密码
     * @return 是否登录成功
     */
    boolean login(String userName, String password);

    /**
     * 修改管理员密码
     *
     * @param userName    管理员账户
     * @param newPassword 新密码
     * @return
     */
    boolean modifyPassword(String userName, String newPassword);
}
