package com.example.wx.Entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 题目表
 * </p>
 *
 * @author "
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Question对象", description = "题目表question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题目ID")
    private String qId;

    @ApiModelProperty(value = "题目内容")
    private String qContent;

    @ApiModelProperty(value = "题目类型（单选、多选、填空）")
    private String qType;

    @ApiModelProperty(value = "表单ID")
    private String nId;

    @ApiModelProperty(value = "是否为必填项")
    private Boolean qIsrequire;

    @ApiModelProperty(value = "题目配置，如至少选几项等")
    private String qSetting;

    @ApiModelProperty(value = "问题描述")
    private String qDescription;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime qCreatetime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime qUpdatetime;

    @TableField(exist = false)
    @ApiModelProperty(value = "题目选项")
    private List<Options> optionsList;

    @ApiModelProperty(value = "是否删除")
    private String qDeleteflag;
}
