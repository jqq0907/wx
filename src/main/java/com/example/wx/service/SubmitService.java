package com.example.wx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.Submit;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author "
 * @since 2021-05-15
 */
public interface SubmitService {

    /**
     * 新增用户提交答案
     *
     * @param submit 实体类
     * @return
     */
    boolean addOne(Submit submit);

    /**
     * 根据nId查询用户提交答案
     *
     * @param nId     表单id
     * @param current 当前页
     * @param size    页大小
     * @return
     */
    Page<Submit> page(String nId, long current, long size);

    /**
     * 判断用户是否已经填写过表单
     *
     * @param nId 表单id
     * @param uId 用户openId
     * @return
     */
    boolean queryByNidAndUid(String nId, String uId);
}
