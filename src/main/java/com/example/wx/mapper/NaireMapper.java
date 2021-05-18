package com.example.wx.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.Naire;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wx.Entity.SelectEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 数据采集表单表 Mapper 接口
 * </p>
 *
 * @author "
 * @since 2021-04-22
 */
public interface NaireMapper extends BaseMapper<Naire> {

    /**
     * 分页查询
     *
     * @param page 页
     * @param naire 查询参数
     * @return
     */
    List<Naire> page(Page<Naire> page, @Param("naire") Naire naire);

    /**
     * 更新表单发布状态
     *
     * @param nId 表单id
     * @return
     */
    int updateStatusById(String nId);

    /**
     * 表单标题select
     *
     * @return
     */
    List<SelectEntity> selectNaireNames();
}
