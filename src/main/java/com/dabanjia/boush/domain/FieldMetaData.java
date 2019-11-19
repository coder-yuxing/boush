package com.dabanjia.boush.domain;

import lombok.Data;

/**
 * java Bean 字段
 * @author GuangRen
 * @date 2019/11/19
 */
@Data
public class FieldMetaData {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段注释
     */
    private String remarks;
}
