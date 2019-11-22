package com.dabanjia.boush.domain;

import com.dabanjia.boush.constant.SqlMappingJavaTypeEnum;
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
     * 注释
     */
    private String remarks;

    /**
     * 是否为主键
     */
    private Boolean isPrimaryKey;

    /**
     * 字段类型映射
     */
    private SqlMappingJavaTypeEnum mappingType;
}
