package com.dabanjia.boush.util;

import com.dabanjia.boush.constant.SqlMappingJavaTypeEnum;
import com.dabanjia.boush.domain.ColumnMetaData;
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

    public JdbcUtils(String configPath) {
        PropertyHandler handler = new PropertyHandler(configPath);
        try {
            config = handler.jdbcPropertyHandle();
            Class.forName(config.getDriverClassName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * jdbc 配置
     */
    private JdbcProperty config;


    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
    }

    /**
     * 获取数据表源数据
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public List<ColumnMetaData> getTableMetaDataByTableName(String tableName) throws Exception {
        final String sql = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns\n" +
                " \t\t\twhere table_name = \"{0}\" and table_schema = (select database()) order by ordinal_position";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(MessageFormat.format(sql, tableName));
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                return parseMetaData(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,preparedStatement, resultSet);
        }
        return null;
    }

    private List<ColumnMetaData> parseMetaData(ResultSet metaData) throws Exception {
        List<ColumnMetaData> columns = new ArrayList<>(metaData.getRow());
        while (metaData.next()) {
            ColumnMetaData columnMetaData = new ColumnMetaData();
            String columnName = metaData.getString("columnName");
            String dataType = metaData.getString("dataType");
            String remarks = metaData.getString("columnComment");
            String key = metaData.getString("columnKey");
            columnMetaData.setColumnName(columnName);
            columnMetaData.setFieldName(StringUtils.underline2Hump(columnName));
            columnMetaData.setMappingType(SqlMappingJavaTypeEnum.getBySqlType(dataType));
            columnMetaData.setRemarks(remarks);
            columnMetaData.setIsPrimaryKey(!StringUtils.isEmpty(key));
            columns.add(columnMetaData);
        }
        return columns;
    }

    private void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
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
}
