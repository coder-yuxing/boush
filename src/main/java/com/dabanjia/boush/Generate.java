package com.dabanjia.boush;

import com.dabanjia.boush.config.GlobalConfig;
import com.dabanjia.boush.config.TableConfig;
import com.dabanjia.boush.domain.*;
import com.dabanjia.boush.util.FreemarkerUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.dabanjia.boush.util.LambdaExceptionUtils.rethrowConsumer;
import static com.dabanjia.boush.util.LambdaExceptionUtils.rethrowFunction;
import static java.util.stream.Collectors.toList;

/**
 * 生成器
 *
 * @author GuangRen
 * @date 2019/11/19
 */
public class Generate {

    private GlobalConfig config;

    public Generate(GlobalConfig config) {
        this.config = config;
    }

    public void execute() throws Exception {
        List<GenerateMetaData> generates = config.getTableConfigs().stream()
                .map(rethrowFunction(c -> GenerateMetaData.build(config, c)))
                .collect(toList());

        List<FreemarkerOutPutMetaData> collect = generates.stream()
                .map(g -> FreemarkerOutPutMetaData.build(config, g))
                .flatMap(Collection::stream)
                .collect(toList());

        collect.forEach(rethrowConsumer(FreemarkerUtils::write));
    }


    public static void main(String[] args) throws Exception {
        TableConfig tableConfig = TableConfig.builder().tableName("uac_user").beanName("User").remarks("用户").build();
        GlobalConfig globalConfig = GlobalConfig.builder()
                .author("GuangRen")
                .datePattern("yyyy/MM/dd")
                .isCover(false)
                .beanModuleName("")
                .beanPackage("com.dabanjia.boush.dao.model")
                .mapperModuleName("")
                .beanMapperPackage("com.dabanjia.boush.dao.mapper")
                .mapperPackage("com.dabanjia.boush.dao.xml")
                .tableConfigs(Collections.singletonList(tableConfig)).build();

        new Generate(globalConfig).execute();

    }

}
