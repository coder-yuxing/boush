package com.dabanjia.boush.util;

import com.dabanjia.boush.domain.ColumnMetaData;
import com.dabanjia.boush.domain.TableMetaData;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author GuangRen
 * @date 2019/11/18
 */
public class JdbcUtilsTest {

    @Test
    public void test() throws Exception {
//        List<ColumnMetaData> columns = JdbcUtils.getTableMetaDataByTableName("uac_user");
//        System.out.println(columns);

        String absolutePath = new File("").getAbsolutePath();

        System.err.println(absolutePath);
    }
}
