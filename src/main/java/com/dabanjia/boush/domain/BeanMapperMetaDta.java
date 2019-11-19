package com.dabanjia.boush.domain;

import lombok.Data;

/**
 * Mapper 接口初始化所需元数据
 *
 * @author GuangRen
 * @date 2019/11/19
 */
@Data
public class BeanMapperMetaDta {

    private String packageName;

    private String beanClassName;

    private String remarks;

    private String author;

    private String date;

    private String name;

    private String beanName;

    private String beanParamName;
}
