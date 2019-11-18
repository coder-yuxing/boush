package com.dabanjia.boush.property;

import lombok.Data;

/**
 * 配置属性
 *
 * @author GuangRen
 * @date 2019/11/18
 */
@Data
public class BoushProperty {

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
}
