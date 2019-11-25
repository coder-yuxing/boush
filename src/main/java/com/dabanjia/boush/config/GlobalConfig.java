package com.dabanjia.boush.config;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 模块配置
 *
 * @author GuangRen
 * @date 2019/11/20
 */
@Data
@Builder
public class GlobalConfig {

    /**
     * 驱动全类名
     */
    private String driverClassName;

    /**
     * url
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String configPath;

    private String beanModuleName;

    private String beanPackage;

    private String mapperModuleName;

    private String mapperPackage;

    private String beanMapperPackage;

    private List<TableConfig> tableConfigs;

    private boolean isCover;

    /**
     * 创建人
     */
    private String author;

    /**
     * 日期格式
     */
    private String datePattern;
}
