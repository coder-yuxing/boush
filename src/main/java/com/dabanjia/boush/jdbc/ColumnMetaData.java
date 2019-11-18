package com.dabanjia.boush.jdbc;

import lombok.Data;

/**
 * 表列元数据
 *
 * @author GuangRen
 * @date 2019/11/18
 */
@Data
public class ColumnMetaData {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列映射字段名
     */
    private String fieldName;

    /**
     * 字段类型
     */
    private String fieldType;
}
