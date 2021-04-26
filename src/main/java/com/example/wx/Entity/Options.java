package com.example.wx.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 题目选项表
 * </p>
 *
 * @author jqq
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Options对象", description="题目选项表options")
public class Options implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "选项ID")
    private String oId;

    @ApiModelProperty(value = "选项内容")
    private String oValue;

    @ApiModelProperty(value = "选项描述")
    private String oDesc;

    @ApiModelProperty(value = "选项图片")
    private String oImage;

    @ApiModelProperty(value = "表单ID")
    private String nId;

    @ApiModelProperty(value = "题目ID")
    private String qId;

    @ApiModelProperty(value = "是否有附加内容")
    private Boolean oIsaddtion;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime oCreatetime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime oUpdatetime;


}
