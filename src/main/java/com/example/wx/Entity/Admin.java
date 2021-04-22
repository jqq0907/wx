package com.example.wx.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author ""
 * @since 2021-04-22
 */
@ApiModel("管理员")
@Data
@EqualsAndHashCode(callSuper = false)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    @ApiModelProperty("管理员id")
    @TableId(value = "a_id")
    private String aId;

    /**
     * 用户名
     */
    @ApiModelProperty("管理员账户")
    private String aUsername;

    /**
     * 密码
     */
    @ApiModelProperty("管理员密码")
    private String aPassword;


}
