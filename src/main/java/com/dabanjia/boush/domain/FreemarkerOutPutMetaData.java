package com.dabanjia.boush.domain;

import com.dabanjia.boush.config.GlobalConfig;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GuangRen
 * @date 2019/11/21
 */
@Data
public class FreemarkerOutPutMetaData {

    private FreemarkerOutPutMetaData() {}

    public static List<FreemarkerOutPutMetaData> build(GlobalConfig config, GenerateMetaData generate) {
        ArrayList<FreemarkerOutPutMetaData> result = new ArrayList<>(3);
        result.add(buildByBeanMetaData(config, generate.getBean()));
        result.add(buildBeanMapperMetaDta(config, generate.getBeanMapper()));
        result.add(buildMapperMetaData(config, generate.getMapper()));
        return result;
    }



    public static FreemarkerOutPutMetaData buildByBeanMetaData(GlobalConfig config, BeanMetaData bean) {
        FreemarkerOutPutMetaData metaData = new FreemarkerOutPutMetaData();
        metaData.setTemplateName("bean.ftl");
        String replace = config.getBeanPackage().replace(".", "/");
        metaData.setFileName("/" + config.getBeanModuleName() + "/src/main/java/" + replace + "/" + bean.getName() + ".java");
        metaData.setCover(config.isCover());
        metaData.setData(bean);
        return metaData;
    }

    public static FreemarkerOutPutMetaData buildBeanMapperMetaDta(GlobalConfig config, BeanMapperMetaDta beanMapper) {
        FreemarkerOutPutMetaData metaData = new FreemarkerOutPutMetaData();
        metaData.setTemplateName("beanMapper.ftl");
        String replace = config.getBeanMapperPackage().replace(".", "/");
        metaData.setFileName("/" + config.getBeanModuleName() + "/src/main/java/" + replace + "/" + beanMapper.getName() + ".java");
        metaData.setCover(config.isCover());
        metaData.setData(beanMapper);
        return metaData;
    }

    public static FreemarkerOutPutMetaData buildMapperMetaData(GlobalConfig config, MapperMetaData mapper) {
        FreemarkerOutPutMetaData metaData = new FreemarkerOutPutMetaData();
        metaData.setTemplateName("mapper.ftl");
        String replace = config.getMapperPackage().replace(".", "/");
        metaData.setFileName("/" + config.getBeanModuleName() + "/src/main/java/" + replace + "/" + mapper.getName() + ".xml");
        metaData.setCover(config.isCover());
        metaData.setData(mapper);
        return metaData;
    }

    private String templatePath = "/template";

    private String templateName;

    private String fileName;

    private boolean isCover;

    private Object data;

}
