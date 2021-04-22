package com.example.wx.service;

import com.example.wx.Entity.Naire;

import java.util.List;

/**
 * <p>
 * 数据采集表单表 服务类
 * </p>
 *
 * @author "
 * @since 2021-04-22
 */
public interface NaireService {

    /**
     * 分页查询
     *
     * @param current 当前页
     * @param size    页大小
     * @param naire   查询参数
     * @return
     */
    List<Naire> page(long current, long size, Naire naire);
}
