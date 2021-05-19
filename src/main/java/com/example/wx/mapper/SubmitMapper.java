package com.example.wx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.Echart;
import com.example.wx.Entity.Submit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author "
 * @since 2021-05-15
 */
public interface SubmitMapper extends BaseMapper<Submit> {

    /**
     * 根据nId用户提交答案的分页查询
     *
     * @param page 分页
     * @param nId  表单id
     * @return
     */
    List<Submit> page(Page<Submit> page, @Param("nId") String nId);

    /**
     * 表单分析图表数据
     *
     * @param nId 表单id
     * @return
     */
    List<Echart> selectAnalysisEchart(@Param("nId") String nId);

    /**
     * 更新分析结果和分析状态
     *
     * @param nId     表单你id
     * @param nResult 分析结果
     * @return
     */
    boolean updateResult(@Param("nId") String nId, @Param("nResult") String nResult);
}
