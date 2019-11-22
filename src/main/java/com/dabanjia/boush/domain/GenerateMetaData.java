package com.dabanjia.boush.domain;

import com.dabanjia.boush.config.GlobalConfig;
import com.dabanjia.boush.config.TableConfig;
import com.dabanjia.boush.util.JdbcUtils;
import lombok.Data;

import java.util.List;

/**
 * @author GuangRen
 * @date 2019/11/21
 */
@Data
public class GenerateMetaData {

    private GenerateMetaData() {}

    public static GenerateMetaData build(GlobalConfig config, TableConfig tableConfig) throws Exception {
        List<ColumnMetaData> columns = JdbcUtils.getTableMetaDataByTableName(tableConfig.getTableName());
        GenerateMetaData generateMetaData = new GenerateMetaData();
        generateMetaData.setBean(BeanMetaData.build(config, tableConfig, columns));
        generateMetaData.setBeanMapper(BeanMapperMetaDta.build(config, tableConfig));
        generateMetaData.setMapper(MapperMetaData.build(config, tableConfig, columns));
        return generateMetaData;
    }

    private BeanMetaData bean;

    private MapperMetaData mapper;

    private BeanMapperMetaDta beanMapper;
}
