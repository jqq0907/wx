package com.example.wx.service.serviceImpl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.Submit;
import com.example.wx.mapper.SubmitMapper;
import com.example.wx.service.SubmitService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author "
 * @since 2021-05-15
 */
@Log4j2
@Service
public class SubmitServiceImpl implements SubmitService {
    @Resource
    private SubmitMapper submitMapper;

    /**
     * 新增用户提交答案
     *
     * @param submit 实体类
     * @return
     */
    @Override
    public boolean addOne(Submit submit) {
        try {
            submitMapper.insert(submit);
            return true;
        } catch (Exception e) {
            log.error("新增用户提交答案失败", e);
            return false;
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
    @Override
    public Page<Submit> page(String nId, long current, long size) {
        Page<Submit> page = new Page<>(current, size);
        try {
            List<Submit> list = submitMapper.page(page, nId);
            // 根据uid去重合并
            ArrayList<Submit> arrayList = list.stream().collect(Collectors.
                    collectingAndThen(Collectors
                            .toCollection(() -> new TreeSet<>(Comparator.comparing(Submit::getUId))), ArrayList::new));
            return page.setRecords(arrayList);
        } catch (Exception e) {
            log.error("后台管理系统查看用户提交记录", e);
            return page.setRecords(null);
        }
    }

    /**
     * 判断用户是否已经填写过表单
     *
     * @param nId 表单id
     * @param uId 用户openId
     * @return
     */
    @Override
    public boolean queryByNidAndUid(String nId, String uId) {
        try {
            Submit submit = submitMapper.selectOne(new QueryWrapper<Submit>()
                    .eq("n_id", nId)
                    .eq("u_id", uId));
            if (ObjectUtil.isNull(submit)) {
                return true;
            }
        } catch (Exception e) {
            log.error("查询用户提交记录失败", e);
        }
        return false;
    }
}
