package com.example.wx.listener;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.wx.Entity.Submit;
import com.example.wx.service.SubmitService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 微信端表单提交消费者
 * @author:
 * @time: 2021/5/15 21:39
 */
@Component
public class SubmitListener {
    @Resource
    private SubmitService submitService;

    @RabbitListener(queues = "item_queue")
    public void msg(String msg) {
        System.out.println(msg);
        // json转实体类
        Submit submit = JSONObject.parseObject(msg, Submit.class);
        // 循环选项
        String[] oIds = submit.getOIds();
        if (oIds != null && oIds.length > 0) {
            // 单选，多选
            for (String oId : oIds) {
                submit.setOId(oId);
                boolean b = submitService.addOne(submit);
            }
        } else {
            // 填空
            boolean b = submitService.addOne(submit);
        }
        System.out.println("消费者消费消息了：" + submit);
    }
}
