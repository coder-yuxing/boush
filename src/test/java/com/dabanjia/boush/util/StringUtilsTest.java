package com.dabanjia.boush.util;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author GuangRen
 * @date 2019/11/18
 */
public class StringUtilsTest {

    @Test
    public void  underline2HumpTest() {
        String str = "update_time";
        assertEquals("updateTime", StringUtils.underline2Hump(str));
        str ="hello_world_lucifer";
        assertEquals("helloWorldLucifer", StringUtils.underline2Hump(str));
    }

}
