package com.dabanjia.boush.jdbc;

import org.junit.Test;

/**
 * @author GuangRen
 * @date 2019/11/18
 */
public class JdbcUtilsTest {

    @Test
    public void test() throws Exception {
        TableMetaData tableMetaData = JdbcUtils.getTableMetaDataByTableName("uac_user");
        System.out.println(tableMetaData);
    }
}
