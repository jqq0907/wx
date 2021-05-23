package com.example.wx.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author "
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Result对象", description = "")
public class Submit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private String uId;

    @ApiModelProperty(value = "表单ID")
    private String nId;

    @ApiModelProperty(value = "题目ID")
    private String qId;

    @ApiModelProperty(value = "选项ID")
    private String oId;

    @ApiModelProperty(value = "结果文字")
    private String oAddtion;

    @ApiModelProperty("创建时间")
    private LocalDateTime sCreateTime;

    /**** 表外字段 ****/

    @TableField(exist = false)
    @ApiModelProperty("选项内容")
    private String oValue;

    @TableField(exist = false)
    @ApiModelProperty("用户活跃时间")
    private LocalDateTime uActiveTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "选项ID")
    private String[] oIds;

    @TableField(exist = false)
    @ApiModelProperty("用户名称")
    private String uName;


}
