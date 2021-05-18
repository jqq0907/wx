package com.example.wx.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description: 下拉框实体类
 * @author:
 * @time: 2021/5/16 12:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "下拉框对象", description = "下拉框对象")
public class SelectEntity implements Serializable {

    /**
     * 下拉框名称
     */
    @ApiModelProperty(value = "下拉框名称")
    private String name;
    /**
     * 下拉框值
     */
    @ApiModelProperty(value = "下拉框值")
    private String value;
}
