package com.example.wx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.Echart;
import com.example.wx.Entity.Naire;
import com.example.wx.Entity.SelectEntity;
import com.example.wx.Entity.Submit;

import java.util.List;
import java.util.Map;

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
    Page<Naire> page(long current, long size, Naire naire);

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

    /**
     * 表单名称select
     *
     * @return
     */
    List<SelectEntity> dicNaireName();

    /**
     * 表单分析图表
     *
     * @param nId 表单id
     * @return
     */
    List<Echart> analysisEchart(String nId);

    /**
     * 表单分析文本数据
     *
     * @param nId     表单id
     * @param current 当前页
     * @param size    页大小
     * @return
     */
    Page<Submit> analysisText(String nId, long current, long size);

    /**
     * 分析提交
     *
     * @param nId     表单id
     * @param nResult 分析结果
     * @return
     */
    boolean analysisSubmit(String nId, String nResult);

    /**
     * 根据id查询表单
     *
     * @param nId 表单id
     * @return
     */
    Naire getOneById(String nId);
}
