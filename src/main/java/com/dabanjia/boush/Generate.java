package com.dabanjia.boush;

import com.dabanjia.boush.config.GlobalConfig;
import com.dabanjia.boush.domain.*;
import com.dabanjia.boush.util.FreemarkerUtils;

import java.util.Collection;
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

}
