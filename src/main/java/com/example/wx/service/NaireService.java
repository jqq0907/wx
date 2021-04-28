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

    /**
     * 新增一个表单
     *
     * @param naire 表单实体类
     * @return
     */
    boolean addOne(Naire naire);

    /**
     * 根据id查询表单
     *
     * @param nId 表单id
     * @return
     */
    Naire queryById(String nId);

    /**
     * 更新一个表单，问题，选项
     *
     * @param naire 表单实体类
     * @return
     */
    boolean updateOne(Naire naire);

    /**
     * 更新表单发布状态
     *
     * @param nId 表单id
     * @return
     */
    boolean updateStatusById(String nId);

    /**
     * 删除表单
     *
     * @param nId 表单id
     * @return
     */
    boolean deleteById(String nId);
}
