package com.example.wx.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.Naire;
import com.example.wx.mapper.NaireMapper;
import com.example.wx.service.NaireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 数据采集表单表 服务实现类
 * </p>
 *
 * @author "
 * @since 2021-04-22
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NaireServiceImpl implements NaireService {

    @Resource
    private NaireMapper naireMapper;

    @Override
    public List<Naire> page(long current, long size, Naire naire) {
        Page<Naire> page = new Page<>(current, size);
        return naireMapper.page(page,naire);
    }
}
