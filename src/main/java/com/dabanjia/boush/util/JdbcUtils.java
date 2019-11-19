package com.dabanjia.boush.util;

import com.dabanjia.boush.constant.SqlMappingJavaTypeEnum;
import com.dabanjia.boush.domain.ColumnMetaData;
import com.dabanjia.boush.domain.TableMetaData;
import com.dabanjia.boush.domain.JdbcProperty;
import com.dabanjia.boush.handle.PropertyHandler;

import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc 工具类
 *
 * @author GuangRen
 * @date 2019/11/18
 */
public class JdbcUtils {

    /**
     * jdbc 配置
     */
    private static JdbcProperty config;

    static {
        PropertyHandler handler = new PropertyHandler();
        try {
            config = handler.jdbcPropertyHandle();
            Class.forName(config.getDriverClassName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
    }

    /**
     * 获取数据表源数据
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public static TableMetaData getTableMetaDataByTableName(String tableName) throws Exception {
        ResultSetMetaData metaData = getMetaData(tableName);
        if (metaData != null) {
            return parseMetaData(metaData);
        }
        return null;
    }

    private static TableMetaData parseMetaData(ResultSetMetaData metaData) throws Exception {
        TableMetaData result = new TableMetaData();
        List<ColumnMetaData> columns = new ArrayList<>(metaData.getColumnCount());
        result.setColumns(columns);
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            ColumnMetaData columnMetaData = new ColumnMetaData();
            columnMetaData.setColumnName(metaData.getColumnName(i));
            columnMetaData.setFieldName(StringUtils.underline2Hump(metaData.getColumnName(i)));
            columnMetaData.setMappingType(SqlMappingJavaTypeEnum.getBySqlType(metaData.getColumnTypeName(i)));
            columns.add(columnMetaData);
        }
        return result;
    }

    private static ResultSetMetaData getMetaData(String tableName)  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "SELECT *  FROM {0} LIMIT 1";
            preparedStatement = connection.prepareStatement(MessageFormat.format(sql, tableName));
            resultSet = preparedStatement.executeQuery();
            return resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
