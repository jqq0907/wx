package com.example.wx.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.wx.Entity.Admin;
import com.example.wx.mapper.AdminMapper;
import com.example.wx.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @description: 管理员表 服务实现类
 * @author:
 * @time:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
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

    /**
     * 修改管理员密码
     *
     * @param userName    管理员账户
     * @param newPassword 新密码
     * @return
     */
    @Override
    public boolean modifyPassword(String userName, String newPassword) {
        //md5加密
        String digest = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        Admin admin = new Admin();
        admin.setAUsername(userName);
        admin.setAPassword(digest);
        return SqlHelper.retBool(adminMapper.update(admin, new UpdateWrapper<Admin>().eq("a_username", admin.getAUsername())));
    }
}
