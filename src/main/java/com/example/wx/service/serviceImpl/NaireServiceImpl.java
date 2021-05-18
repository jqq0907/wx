package com.example.wx.service.serviceImpl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.Naire;
import com.example.wx.Entity.Options;
import com.example.wx.Entity.Question;
import com.example.wx.Entity.SelectEntity;
import com.example.wx.mapper.NaireMapper;
import com.example.wx.mapper.OptionsMapper;
import com.example.wx.mapper.QuestionMapper;
import com.example.wx.service.NaireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
    public Page<Naire> page(long current, long size, Naire naire) {
        Page<Naire> page = new Page<>(current, size);
        try {
            List<Naire> list = naireMapper.page(page, naire);
            return page.setRecords(list);
        } catch (Exception e) {
            log.error("微信端分页查询表单", e);
            return page;
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
            // 创建时间
            naire.setNCreattime(LocalDateTime.now());
            // 修改时间
            naire.setNUpdatetime(LocalDateTime.now());
            // 未删除
            naire.setNDeleteflag("00");
            // 管理员id
            naire.setAId("");
            // 未分析
            naire.setNAnalysisFlag("0");
            naireMapper.insert(naire);
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
            // 未删除
            question.setQDeleteflag("00");
            questionMapper.insert(question);
            //3.新增选项
            List<Options> optionsList = question.getOptionsList();
            System.out.println(optionsList);
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
                // 未删除
                item.setODeleteflag("00");
                optionsMapper.insert(item);
            });
            return true;
        } catch (Exception e) {
            log.error("新增表单失败", e);
            return false;
        }
    }

    /**
     * 根据id查询表单
     *
     * @param nId 表单id
     * @return
     */
    @Override
    public Naire queryById(String nId) {
        // 根据id查询表单数据
        Naire naire = naireMapper.selectById(nId);
        if (ObjectUtil.isNull(naire)) {
            return new Naire();
        }
        // 根据表单id查询表单问题
        Question question = questionMapper.selectOne(new QueryWrapper<Question>()
                .eq("n_id", nId)
                .eq("q_deleteflag", "00"));
        // 根据表单id和题目id查询题目选项
        List<Options> optionsList = optionsMapper.selectList(new QueryWrapper<Options>()
                .eq("n_id", nId)
                .eq("q_id", question.getQId())
                .eq("o_deleteflag", "00"));

        question.setOptionsList(optionsList);
        naire.setQuestion(question);
        return naire;
    }

    /**
     * 更新一个表单，问题，选项
     *
     * @param naire 表单实体类
     * @return
     */
    @Override
    public boolean updateOne(Naire naire) {
        try {
            //1.更新表单
            // 修改时间
            naire.setNUpdatetime(LocalDateTime.now());
            naireMapper.updateById(naire);
            //2.更新表单问题
            Question question = naire.getQuestion();
            // 修改时间
            question.setQUpdatetime(LocalDateTime.now());
            questionMapper.update(question, new UpdateWrapper<Question>()
                    .eq("q_id", question.getQId()));
            //3.更新选项
            List<Options> optionsList = question.getOptionsList();
            //删除之前选项
            Options options = new Options();
            options.setODeleteflag("01");
            optionsMapper.update(options, new UpdateWrapper<Options>()
                    .eq("n_id", naire.getNId())
                    .eq("q_id", question.getQId()));
            // 新增修改的选项
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
                // 未删除
                item.setODeleteflag("00");
                optionsMapper.insert(item);
            });
            return true;
        } catch (Exception e) {
            log.error("修改表单失败", e);
            return false;
        }
    }

    /**
     * 更新表单发布状态
     *
     * @param nId 表单id
     * @return
     */
    @Override
    public boolean updateStatusById(String nId) {
        try {
            naireMapper.updateStatusById(nId);
            return true;
        } catch (Exception e) {
            log.error("更新表单发布状态失败", e);
            return false;
        }
    }

    /**
     * 删除表单
     *
     * @param nId 表单id
     * @return
     */
    @Override
    public boolean deleteById(String nId) {
        try {
            //1.逻辑删除表单
            Naire naire = new Naire();
            naire.setNId(nId);
            naire.setNDeleteflag("01");
            naireMapper.updateById(naire);
            //2.逻辑删除表单问题
            Question question = new Question();
            question.setNId(nId);
            question.setQDeleteflag("01");
            questionMapper.update(question, new QueryWrapper<Question>()
                    .eq("n_id", nId));
            //3.逻辑删除问题选项
            Options options = new Options();
            options.setNId(nId);
            options.setODeleteflag("01");
            optionsMapper.update(options, new QueryWrapper<Options>()
                    .eq("n_id", nId));
            return true;
        } catch (Exception e) {
            log.error("删除表单失败", e);
            return false;
        }
    }

    /**
     * 表单名称select
     *
     * @return
     */
    @Override
    public List<SelectEntity> dicNaireName() {
        return naireMapper.selectNaireNames();
    }
}
