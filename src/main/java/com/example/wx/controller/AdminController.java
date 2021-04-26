package com.example.wx.controller;

import cn.hutool.core.util.StrUtil;
import com.example.wx.service.AdminService;
import com.example.wx.system.rest.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author ""
 * @since
 */
@Api(value = "管理员api", description = "管理员controller")
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService loginService;

    /**
     * 登录
     *
     * @param userName 管理员账户
     * @param password 密码
     * @return
     */
    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public Result login(@ApiParam(value = "管理员账户", required = true) @RequestParam(required = true) String userName,
                        @ApiParam(value = "管理员密码", required = true) @RequestParam(required = true) String password) {
        if (StrUtil.isEmpty(userName) || StrUtil.isEmpty(password)) {
            return Result.fail("", "用户名或者密码不能为空");
        }
        boolean login = loginService.login(userName, password);
        if (login) {
            return Result.success(true, "登录成功");
        } else {
            return Result.fail(false, "登录失败,用户名或密码错误");
        }
    }

    @ApiOperation("修改管理员密码")
    @GetMapping("/modify")
    public Result modifyPassword(@ApiParam(value = "管理员账户", required = true) @RequestParam(required = true) String userName,
                                 @ApiParam(value = "旧密码", required = true) @RequestParam(required = true) String oldPassword,
                                 @ApiParam(value = "新密码", required = true) @RequestParam(required = true) String newPassword) {
        boolean login = loginService.login(userName, oldPassword);
        if (!login) {
            return Result.fail(false, "原密码错误");
        }
        boolean modify = loginService.modifyPassword(userName, newPassword);
        if (modify) {
            return Result.success(true, "修改成功");
        } else {
            return Result.fail(false, "修改失败");
        }
    }
}
