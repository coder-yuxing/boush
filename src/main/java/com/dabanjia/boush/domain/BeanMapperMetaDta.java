package com.dabanjia.boush.domain;

import com.dabanjia.boush.config.GlobalConfig;
import com.dabanjia.boush.config.TableConfig;
import com.dabanjia.boush.util.DateUtils;
import com.dabanjia.boush.util.StringUtils;
import lombok.Data;

import java.util.Date;

/**
 * Mapper 接口初始化所需元数据
 *
 * @author GuangRen
 * @date 2019/11/19
 */
@Data
public class BeanMapperMetaDta {

    private BeanMapperMetaDta() {}

    public static BeanMapperMetaDta  build(GlobalConfig config, TableConfig tableConfig) {
        BeanMapperMetaDta beanMapperMetaDta = new BeanMapperMetaDta();
        beanMapperMetaDta.setPackageName(config.getBeanMapperPackage());
        beanMapperMetaDta.setBeanClassName(config.getBeanPackage() + "." + tableConfig.getBeanName());
        beanMapperMetaDta.setRemarks(tableConfig.getRemarks());
        beanMapperMetaDta.setAuthor(config.getAuthor());
        beanMapperMetaDta.setDate(DateUtils.formatDateTime(new Date(), config.getDatePattern()));
        beanMapperMetaDta.setName(tableConfig.getBeanName() + "Mapper");
        beanMapperMetaDta.setBeanName(tableConfig.getBeanName());
        beanMapperMetaDta.setBeanParamName(StringUtils.toUpperCaseFirstLetter(tableConfig.getBeanName()));
        return beanMapperMetaDta;
    }

    private String packageName;

    private String beanClassName;

    private String remarks;

    private String author;

    private String date;

    private String name;

    private String beanName;

    private String beanParamName;
}
