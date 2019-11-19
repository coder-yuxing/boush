package com.dabanjia.boush.property;

import com.dabanjia.boush.domain.JdbcProperty;
import com.dabanjia.boush.handle.PropertyHandler;
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
        JdbcProperty jdbcProperty = propertyHandler.jdbcPropertyHandle();
        Assert.assertEquals("Wge2I9RQZEZ9PJYQ", jdbcProperty.getPassword());
    }
}
