package com.dabanjia.boush.domain;

import lombok.Data;

import java.util.List;

/**
 * Mapper xml 初始化所需元数据
 * @author GuangRen
 * @date 2019/11/19
 */
@Data
public class MapperMetaData {

    /**
     * mapper namespace
     */
    private String namespace;

    /**
     * bean 全类名
     */
    private String beanClassName;

    /**
     * 字段映射
     */
    private List<ColumnMetaData> resultMap;

    /**
     * 表列名拼接  ,
     */
    private String columnNameList;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 主键类型
     */
    private String idType;



    private String idColumnName;


}
