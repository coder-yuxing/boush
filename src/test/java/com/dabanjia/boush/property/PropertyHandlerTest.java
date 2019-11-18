package com.dabanjia.boush.property;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author GuangRen
 * @date 2019/11/18
 */
public class PropertyHandlerTest {

    @Test
    public void  handleTest() throws Exception {

        PropertyHandler propertyHandler = new PropertyHandler();
        BoushProperty handle = propertyHandler.handle();
        Assert.assertEquals("Wge2I9RQZEZ9PJYQ", handle.getPassword());
    }
}
