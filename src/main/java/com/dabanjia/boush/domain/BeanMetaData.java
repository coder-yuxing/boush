package com.dabanjia.boush.domain;

import lombok.Data;

import java.util.List;

/**
 * Java Bean 初始化所需元数据
 *
 * @author GuangRen
 * @date 2019/11/19
 */
@Data
public class BeanMetaData {

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

}
