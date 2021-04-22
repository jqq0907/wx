package com.example.wx.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.wx.Entity.Admin;
import com.example.wx.mapper.AdminMapper;
import com.example.wx.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @description: 登录服务层
 * @author:
 * @time:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {
    @Resource
    private AdminMapper adminMapper;

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 用户密码
     * @return
     */
    @Override
    public boolean login(String userName, String password) {
        // md5加密
        String digest = DigestUtils.md5DigestAsHex(password.getBytes());
        return SqlHelper.retBool(adminMapper.selectCount(new QueryWrapper<Admin>()
                .eq("a_username", userName)
                .eq("a_password", digest)));
    }
}
