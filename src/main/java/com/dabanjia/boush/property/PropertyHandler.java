package com.dabanjia.boush.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件处理类
 *
 * @author GuangRen
 * @date 2019/11/18
 */
public class PropertyHandler {

    /**
     * 配置文件地址
     */
    private static final String CONFIG_PATH  = "/generate.properties";

    /**
     * 解析配置文件
     *
     * @return
     * @throws IOException
     */
    public JdbcProperty jdbcPropertyHandle() throws IOException {
        Properties config = this.readConfig();
        JdbcProperty property = new JdbcProperty();
        property.setDriverClassName(config.getProperty("driver-class-name"));
        property.setUrl(config.getProperty("url"));
        property.setUsername(config.getProperty("username"));
        property.setPassword(config.getProperty("password"));
        return property;
    }

    /**
     * 读取配置
     *
     * @return Properties
     * @throws IOException
     */
    private Properties readConfig() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream(CONFIG_PATH);
        properties.load(inputStream);
        return properties;
    }
}
