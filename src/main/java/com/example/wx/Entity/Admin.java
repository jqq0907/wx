package com.example.wx.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@Data
@EqualsAndHashCode(callSuper = false)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    @TableId(value = "a_id", type = IdType.AUTO)
    private Long aId;

    /**
     * 用户名
     */
    private String aUsername;

    /**
     * 密码
     */
    private String aPassword;


}
