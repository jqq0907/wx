package com.example.wx.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.*;
import com.example.wx.service.NaireService;
import com.example.wx.service.QuestionService;
import com.example.wx.system.rest.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据采集表单表 前端控制器
 * </p>
 *
 * @author "
 * @since 2021-04-22
 */
@Api(value = "采集表单api", description = "采集表单controller")
@Slf4j
@RestController
@RequestMapping("/naire")
public class NaireController {

    @Resource
    private NaireService naireService;
    @Resource
    private QuestionService questionService;

    /**
     * @param nTitle  表单标题
     * @param nStatus 发布状态
     * @param current 当前页
     * @param size    页大小
     * @return
     */
    @ApiOperation("分页查询表单")
    @GetMapping("/page")
    public Result page(@ApiParam(value = "表单标题", required = true) @RequestParam String nTitle,
                       @ApiParam(value = "表单状态", required = true) @RequestParam String nStatus,
                       @ApiParam(value = "当前页", defaultValue = "1", example = "1") @RequestParam(defaultValue = "1") long current,
                       @ApiParam(value = "页大小", defaultValue = "10", example = "10") @RequestParam(defaultValue = "10") long size) {
        Naire naire = new Naire();
        // 表单标题
        naire.setNTitle(nTitle);
        // 是否发布
        naire.setNStatus(nStatus);
        Page<Naire> page = naireService.page(current, size, naire);
        return Result.success(page, "分页数据");
    }

    /**
     * 微信端分页查询表单
     *
     * @param current 当前页
     * @param size    页大小
     * @return
     */
    @ApiOperation("微信端分页查询表单")
    @GetMapping("/pageForWx")
    public Result pageForWx(@ApiParam(value = "当前页", defaultValue = "1", example = "1") @RequestParam(defaultValue = "1") long current,
                            @ApiParam(value = "页大小", defaultValue = "10", example = "10") @RequestParam(defaultValue = "10") long size) {
        Naire naire = new Naire();
        // 表单标题
        naire.setNTitle("");
        // 是否发布
        naire.setNStatus("1");
        Page<Naire> page = naireService.page(current, size, naire);
        return Result.success(page, "微信端分页数据");
    }

    /**
     * 新增一个表单，问题，选项
     *
     * @param naire 表单实体类
     * @return
     */
    @ApiOperation("新增一个表单")
    @ApiImplicitParam(value = "表单实体类", name = "naire")
    @PostMapping("/addOne")
    public Result addOne(@RequestBody Naire naire) {
        System.out.println(naire);
        boolean b = naireService.addOne(naire);
        return Result.result(b);
    }

    /**
     * 根据表单id查询表单
     *
     * @param nId 表单id
     * @return
     */
    @ApiOperation("根据id查询表单")
    @GetMapping("/queryById")
    public Result queryById(@ApiParam(value = "表单id", required = true) @RequestParam String nId) {
        Naire naire = naireService.queryById(nId);
        return Result.success(naire, "查询成功");
    }

    /**
     * 更新一个表单，问题，选项
     *
     * @param naire 表单实体类
     * @return
     */
    @ApiOperation("更新一个表单")
    @ApiImplicitParam(value = "表单实体类", name = "naire")
    @PostMapping("/updateOne")
    public Result updateOne(@RequestBody Naire naire) {
        boolean b = naireService.updateOne(naire);
        return Result.result(b);
    }

    /**
     * 发布/取消发布表单
     *
     * @param nId 表单id
     * @return
     */
    @ApiOperation("发布/取消发布表单")
    @GetMapping("/publishNaire")
    public Result publishNaire(@ApiParam(value = "表单id", required = true) @RequestParam String nId) {
        boolean b = naireService.updateStatusById(nId);
        return Result.result(b);
    }

    /**
     * 删除表单
     *
     * @param nId 表单id
     * @return
     */
    @ApiOperation("删除表单")
    @GetMapping("/deleteById")
    public Result deleteById(@ApiParam(value = "表单id", required = true) @RequestParam String nId) {
        boolean b = naireService.deleteById(nId);
        return Result.result(b);
    }

    /**
     * 表单名称select
     *
     * @return
     */
    @ApiOperation("表单名称select")
    @GetMapping("/dicNaireName")
    public Result dicNaireName() {
        List<SelectEntity> list = naireService.dicNaireName();
        return Result.success(list);
    }

    /**
     * 分析图表数据
     *
     * @param nId 表单id
     * @return
     */
    @ApiOperation("分析图表数据")
    @GetMapping("/analysisEchart")
    public Result analysisEchart(@ApiParam(value = "表单id", required = true) @RequestParam String nId) {
        List<Echart> echarts = naireService.analysisEchart(nId);
        return Result.success(echarts);
    }

    /**
     * 分析文本数据(分页)
     *
     * @param nId     表单id
     * @param current 当前页
     * @param size    页大小
     * @return
     */
    @ApiOperation("分析文本数据(分页)")
    @GetMapping("/analysisText")
    public Result analysisText(@ApiParam(value = "表单id", required = true) @RequestParam String nId,
                               @ApiParam(value = "当前页", defaultValue = "1", example = "1") @RequestParam(defaultValue = "1") long current,
                               @ApiParam(value = "页大小", defaultValue = "10", example = "10") @RequestParam(defaultValue = "10") long size) {
        Page<Submit> page = naireService.analysisText(nId, current, size);
        return Result.success(page);
    }

    /**
     * 分析提交
     *
     * @param nId     表单id
     * @param nResult 分析结果
     * @return
     */
    @ApiOperation("分析提交")
    @GetMapping("/analysisSubmit")
    public Result analysisSubmit(@ApiParam(value = "表单id", required = true) @RequestParam String nId,
                                 @ApiParam(value = "分析结果", required = true) @RequestParam String nResult) {
        boolean b = naireService.analysisSubmit(nId, nResult);
        return Result.result(b);
    }

    /**
     * 微信端展示结果
     *
     * @param nId 表单id
     * @return
     */
    @ApiOperation("微信端展示结果")
    @GetMapping("/getOneById")
    public Result getOneById(@ApiParam(value = "表单id", required = true) @RequestParam String nId) {
        Naire naire = naireService.getOneById(nId);
        return Result.success(naire);
    }
}
