package com.dabanjia.boush.domain;

import com.dabanjia.boush.config.GlobalConfig;
import com.dabanjia.boush.config.TableConfig;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Mapper xml 初始化所需元数据
 * @author GuangRen
 * @date 2019/11/19
 */
@Data
public class MapperMetaData {

    private MapperMetaData() {}

    public static MapperMetaData build(GlobalConfig config, TableConfig tableConfig, List<ColumnMetaData> columns) {
        MapperMetaData mapperMetaData = new MapperMetaData();
        mapperMetaData.setNamespace(config.getBeanMapperPackage() + "." + tableConfig.getBeanName() + "Mapper");
        mapperMetaData.setBeanClassName(config.getBeanPackage() + "." + tableConfig.getBeanName());
        mapperMetaData.setResultMap(columns);
        mapperMetaData.setColumnNameList(buildColumnNameList(columns));
        mapperMetaData.setTableName(tableConfig.getTableName());
        List<ColumnMetaData> collect = columns.stream().filter(ColumnMetaData::getIsPrimaryKey).limit(1).collect(toList());
        ColumnMetaData columnMetaData = collect.get(0);
        mapperMetaData.setIdColumnName(columnMetaData.getColumnName());
        mapperMetaData.setIdType(columnMetaData.getMappingType().getJavaType());
        mapperMetaData.setName(tableConfig.getBeanName() + "Mapper");
        return mapperMetaData;
    }

    private static String buildColumnNameList(List<ColumnMetaData> columns) {
        return columns.stream().map(ColumnMetaData::getColumnName).collect(Collectors.joining(", "));
    }

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

    private String name;

    /**
     * 主键类型
     */
    private String idType;


    private String idColumnName;

}
