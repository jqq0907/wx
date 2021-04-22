package com.example.wx.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wx.Entity.Naire;
import com.example.wx.service.NaireService;
import com.example.wx.system.rest.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
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
@Api("采集表单api")
@Slf4j
@RestController
@RequestMapping("/naire")
public class NaireController {

    @Resource
    private NaireService naireService;

    /**
     * @param nTitle
     * @param nStatus
     * @param current
     * @param size
     * @return
     */
    @ApiOperation("分页查询表单")
    @GetMapping("/page")
    public Result page(@ApiParam(value = "表单标题") @RequestParam(required = false) String nTitle,
                       @ApiParam(value = "表单状态") @RequestParam(required = false) Boolean nStatus,
                       @ApiParam(value = "当前页", defaultValue = "1") @RequestParam(defaultValue = "1") long current,
                       @ApiParam(value = "页大小", defaultValue = "10") @RequestParam(defaultValue = "10") long size) {
        Naire naire = new Naire();
        naire.setNTitle(nTitle);
        naire.setNStatus(nStatus);
        List<Naire> list = naireService.page(current,size,naire);
        return null;
    }

    /**
     * 新增一个表单
     *
     * @param naire 表单实体类
     * @return
     */
    @ApiOperation("新增一个表单")
    @PostMapping("/addOne")
    public Result addOne(Naire naire) {
        return null;
    }
}
