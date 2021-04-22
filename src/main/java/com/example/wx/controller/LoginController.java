package com.example.wx.controller;

import com.example.wx.service.LoginService;
import com.example.wx.system.rest.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author:
 * @time:
 */
@RestController
@RequestMapping("/admin")
public class LoginController {
    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam(required = true) String userName,
                        @RequestParam(required = true) String password) {
        boolean login = loginService.login(userName, password);
        return Result.result(login);
    }

}
