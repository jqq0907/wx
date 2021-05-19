package com.example.wx.Entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 图表实体类
 * @author: jqq
 * @time: 2021/5/19 12:31
 */
@Data
public class Echart implements Serializable {

    /**
     * 标题
     */
    private String name;

    /**
     * 数据
     */
    private String value;
}
