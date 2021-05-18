package com.example.wx.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.Submit;
import com.example.wx.service.SubmitService;
import com.example.wx.system.config.RabbitMQConfig;
import com.example.wx.system.rest.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author "
 * @since 2021-05-15
 */
@Api(value = "微信端表单提交api", description = "微信端表单提交controller")
@RestController
@RequestMapping("/submit")
public class SubmitController {
    @Resource
    private SubmitService submitService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 微信端提交表单提交生产者
     *
     * @param submit 提交表单
     * @return
     */
    @ApiOperation("微信端提交表单")
    @PostMapping("/addOne")
    public Result addOne(@ApiParam(value = "提交表单", required = true) @RequestBody Submit submit) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_TOPIC_EXCHANGE, "item.springboot-rabbitmq", JSON.toJSONString(submit));
            return Result.msg("提交成功");
        } catch (AmqpException e) {
            e.printStackTrace();
            return Result.fail("提交失败");
        }
    }

    /**
     * 后台管理系统查看用户提交记录
     *
     * @param nId     表单id
     * @param current 当前页
     * @param size    页大小
     * @return
     */
    @ApiOperation("后台管理系统查看用户提交记录")
    @GetMapping("/pageForPc")
    public Result pageForPc(@ApiParam(value = "表单id", required = true) @RequestParam String nId,
                            @ApiParam(value = "当前页", defaultValue = "1", example = "1") @RequestParam(defaultValue = "1") long current,
                            @ApiParam(value = "页大小", defaultValue = "10", example = "10") @RequestParam(defaultValue = "20") long size) {
        Page<Submit> page = submitService.page(nId, current, size);
        return Result.success(page);
    }

    /**
     * 判断用户是否已经填写过表单
     *
     * @param nId 表单id
     * @param uId 用户openId
     * @return
     */
    @ApiOperation("判断用户是否已经填写过表单")
    @GetMapping("/writeForm")
    public Result writeForm(@ApiParam(value = "表单id", required = true) @RequestParam String nId,
                            @ApiParam(value = "用户openId", required = true) @RequestParam String uId) {
        boolean b = submitService.queryByNidAndUid(nId, uId);
        return Result.success(b);
    }
}
