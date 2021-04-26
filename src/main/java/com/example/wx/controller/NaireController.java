package com.example.wx.controller;


import com.example.wx.Entity.Naire;
import com.example.wx.service.NaireService;
import com.example.wx.system.rest.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * @param nTitle  表单标题
     * @param nStatus 发布状态
     * @param current 当前页
     * @param size    页大小
     * @return
     */
    @ApiOperation("分页查询表单")
    @GetMapping("/page")
    public Result page(@ApiParam(value = "表单标题") @RequestParam(required = false) String nTitle,
                       @ApiParam(value = "表单状态") @RequestParam(required = false) Boolean nStatus,
                       @ApiParam(value = "当前页", defaultValue = "1", example = "1") @RequestParam(defaultValue = "1") long current,
                       @ApiParam(value = "页大小", defaultValue = "10", example = "10") @RequestParam(defaultValue = "10") long size) {
        Naire naire = new Naire();
        naire.setNTitle(nTitle);
        naire.setNStatus(nStatus);
        List<Naire> list = naireService.page(current, size, naire);
        return Result.success(list, "分页数据");
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
    public Result addOne(@RequestBody(required = true) Naire naire) {
        naireService.addOne(naire);
        return null;
    }
}
