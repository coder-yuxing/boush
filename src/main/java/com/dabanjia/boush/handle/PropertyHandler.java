package com.dabanjia.boush.handle;

import com.dabanjia.boush.domain.JdbcProperty;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 配置文件处理类
 *
 * @author GuangRen
 * @date 2019/11/18
 */
@Slf4j
public class PropertyHandler {

    /**
     * 配置文件地址
     */
    private String configPath;

    public PropertyHandler(String configPath) {
        this.configPath = configPath;
    }

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
        properties.load(new InputStreamReader(PropertyHandler.class.getResourceAsStream(configPath), StandardCharsets.UTF_8));
        return properties;
    }
}
