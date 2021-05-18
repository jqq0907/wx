package com.example.wx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
}
