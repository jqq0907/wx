package com.example.wx.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author "
 * @since 2021-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Users对象", description="用户表")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private String uId;

    @ApiModelProperty(value = "姓名")
    private String uName;

    @ApiModelProperty(value = "手机号")
    private String uTel;

    @ApiModelProperty(value = "邮箱")
    private String uEmail;

    @ApiModelProperty(value = "用户最近的活跃时间")
    private LocalDateTime uActiveTime;


}
