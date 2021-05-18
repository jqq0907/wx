package com.example.wx.controller;


import cn.hutool.core.util.StrUtil;
import com.example.wx.service.UsersService;
import com.example.wx.system.rest.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author "
 * @since 2021-05-16
 */
@Api(value = "微信用户api", description = "微信用户controller")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    /**
     * 微信小程序登录
     *
     * @param code     登录code
     * @param userName 微信用户名称
     * @return
     */
    @ApiOperation("微信小程序登录")
    @GetMapping("/loginWx")
    public Result loginWx(@ApiParam(value = "微信登录code", required = true) @RequestParam String code,
                          @ApiParam(value = "微信用户名", required = true) @RequestParam String userName) {
        String openId = usersService.loginWx(code, userName);
        if (StrUtil.isEmpty(openId)) {
            return Result.fail("微信登录失败");
        }
        return Result.success(openId);
    }
}
