package com.dabanjia.boush.config;

import lombok.Builder;
import lombok.Data;

/**
 * @author GuangRen
 * @date 2019/11/19
 */
@Data
@Builder
public class TableConfig {

    /**
     * 表名
     */
    private String tableName;

    /**
     * java bean name
     */
    private String beanName;

    /**
     * 注释
     */
    private String remarks;

}

