package com.dabanjia.boush.domain;

import com.dabanjia.boush.config.GlobalConfig;
import com.dabanjia.boush.config.TableConfig;
import com.dabanjia.boush.constant.SqlMappingJavaTypeEnum;
import com.dabanjia.boush.util.DateUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Java Bean 初始化所需元数据
 *
 * @author GuangRen
 * @date 2019/11/19
 */
@Data
public class BeanMetaData {

    private BeanMetaData() {}

    public static BeanMetaData build(GlobalConfig config, TableConfig tableConfig, List<ColumnMetaData> columns) {
        BeanMetaData beanMetaData = new BeanMetaData();
        beanMetaData.setPackageName(config.getBeanPackage());
        beanMetaData.setImportQuotes(buildImportQuotes(columns));
        beanMetaData.setAuthor(config.getAuthor());
        beanMetaData.setDate(DateUtils.formatDateTime(new Date(), config.getDatePattern()));
        beanMetaData.setName(tableConfig.getBeanName());
        beanMetaData.setRemarks(tableConfig.getRemarks());
        beanMetaData.setFields(buildFieldMetaData(columns));
        return beanMetaData;
    }
    /**
     * bean 所属包
     */
    private String packageName;

    /**
     * 所需引用
     */
    private List<String> importQuotes;

    /**
     * 作者
     */
    private String author;

    /**
     * 日期
     */
    private String date;

    /**
     * bean name
     */
    private String name;

    /**
     * java bean remarks
     */
    private String remarks;
    /**
     * bean 字段列表
     */
    private List<FieldMetaData> fields;

    private static List<String> buildImportQuotes(List<ColumnMetaData> columns) {
        return columns.stream()
                .map(ColumnMetaData::getMappingType)
                .filter(SqlMappingJavaTypeEnum::isNeedImport)
                .map(SqlMappingJavaTypeEnum::getClassName)
                .collect(toList());
    }

    private static List<FieldMetaData> buildFieldMetaData(List<ColumnMetaData> columns) {
        return columns.stream().map(BeanMetaData::buildFieldMetaData).collect(toList());
    }

    private static FieldMetaData buildFieldMetaData(ColumnMetaData column) {
        FieldMetaData fieldMetaData = new FieldMetaData();
        fieldMetaData.setName(column.getFieldName());
        fieldMetaData.setRemarks(column.getRemarks());
        fieldMetaData.setType(column.getMappingType().getJavaType());
        return fieldMetaData;
    }

}
