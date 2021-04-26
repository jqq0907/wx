package com.example.wx.service.serviceImpl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.wx.Entity.Naire;
import com.example.wx.Entity.Options;
import com.example.wx.Entity.Question;
import com.example.wx.mapper.NaireMapper;
import com.example.wx.mapper.OptionsMapper;
import com.example.wx.mapper.QuestionMapper;
import com.example.wx.service.NaireService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
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
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private OptionsMapper optionsMapper;

    @Override
    public List<Naire> page(long current, long size, Naire naire) {
        Page<Naire> page = new Page<>(current, size);
        try {
            return naireMapper.page(page, naire);
        } catch (Exception e) {
            log.error("分页查询表单失败", e);
            return null;
        }
    }

    /**
     * 新增一个表单，问题，选项
     *
     * @param naire 表单实体类
     * @return
     */
    @Override
    public boolean addOne(Naire naire) {
        try {
            //1.新增表单
            // 表单主键id
            naire.setNId(IdUtil.simpleUUID());
            // 修改时间
            naire.setNUpdatetime(LocalDateTime.now());
            // 未删除
            naire.setNDeleteflag("00");
            // 管理员id
            naire.setAId("");
            boolean b = SqlHelper.retBool(naireMapper.insert(naire));
            //2.新增表单问题
            Question question = naire.getQuestion();
            // 题目主键id
            question.setQId(IdUtil.simpleUUID());
            // 表单id
            question.setNId(naire.getNId());
            // 创建时间
            question.setQCreatetime(LocalDateTime.now());
            // 修改时间
            question.setQUpdatetime(LocalDateTime.now());
            SqlHelper.retBool(questionMapper.insert(question));
            //3.新增选项
            List<Options> optionsList = question.getOptionsList();
            optionsList.forEach(item -> {
                // 题目主键id
                item.setOId(IdUtil.simpleUUID());
                // 表单id
                item.setNId(naire.getNId());
                // 题目id
                item.setQId(question.getQId());
                // 创建时间
                item.setOCreatetime(LocalDateTime.now());
                // 更新时间
                item.setOUpdatetime(LocalDateTime.now());
                optionsMapper.insert(item);
            });
            return true;
        } catch (Exception e) {
            log.error("新增表单失败", e);
            return false;
        }
    }
}
