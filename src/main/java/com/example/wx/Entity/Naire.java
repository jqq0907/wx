package com.example.wx.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据采集表单表
 * </p>
 *
 * @author "
 * @since 2021-04-22
 */
@ApiModel(value = "表单表", description = "表单表naire")
@Data
@EqualsAndHashCode(callSuper = false)
public class Naire implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表单id
     */
    @TableId(value = "n_id")
    @ApiModelProperty("表单id")
    private String nId;

    /**
     * 管理员id
     */
    @ApiModelProperty("管理员id")
    private String aId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private LocalDateTime nCreattime;

    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("截止时间")
    private LocalDateTime nDeadline;

    /**
     * 表单标题
     */
    @ApiModelProperty("表单标题")
    private String nTitle;

    /**
     * 表单状态0未发布，1已发布
     */
    @ApiModelProperty("表单状态")
    private String nStatus;

    /**
     * 表单介绍
     */
    @ApiModelProperty("表单介绍")
    private String nIntro;

    /**
     * 表单相关配置(JSON)
     */
    @ApiModelProperty("表单相关配置")
    private String nOptions;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("修改时间")
    private LocalDateTime nUpdatetime;

    /**
     * 是否删除 00未删除01删除
     */
    @ApiModelProperty("是否删除")
    private String nDeleteflag;

    /**
     * 是否分析 0未分析1已分析
     */
    @ApiModelProperty("是否分析标志")
    private String nAnalysisFlag;

    /**
     * 分析结果
     */
    @ApiModelProperty("分析结果")
    private String nResult;

    /**** 表外字段 ****/

    /**
     * 表单问题
     */
    @TableField(exist = false)
    @ApiModelProperty("表单问题")
    private Question question;

    /**
     * 截止日期str
     */
    @TableField(exist = false)
    @ApiModelProperty("截止日期str")
    private String nDeadlineStr;


}
