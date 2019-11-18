package com.dabanjia.boush.jdbc;

import lombok.Data;

import java.util.List;

/**
 * 数据表元数据
 *
 * @author GuangRen
 * @date 2019/11/18
 */
@Data
public class TableMetaData {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 列数据
     */
    private List<ColumnMetaData> columns;
}
