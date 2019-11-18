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
     * @return BoushProperty
     * @throws IOException
     */
    public BoushProperty handle() throws IOException {
        Properties config = this.readConfig();
        BoushProperty boushProperty = new BoushProperty();
        boushProperty.setDriverClassName(config.getProperty("driver-class-name"));
        boushProperty.setUrl(config.getProperty("url"));
        boushProperty.setUsername(config.getProperty("username"));
        boushProperty.setPassword(config.getProperty("password"));
        return boushProperty;
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
