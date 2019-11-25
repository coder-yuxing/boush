package com.dabanjia.boush.util;

import com.dabanjia.boush.domain.FreemarkerOutPutMetaData;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;

/**
 * freemarker 导出工具类
 * @author GuangRen
 * @date 2019/11/21
 */
public class FreemarkerUtils {

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_29);

    private FreemarkerUtils() {}

    public static Configuration getConfiguration() {
        return CONFIGURATION;
    }

    public static ClassTemplateLoader getFileTemplateLoader(String path) throws IOException {
        return new ClassTemplateLoader(FreemarkerUtils.class, path);
    }

    public static void write(FreemarkerOutPutMetaData metaData) throws Exception {
        ClassTemplateLoader classTemplateLoader = getFileTemplateLoader(metaData.getTemplatePath());
        CONFIGURATION.setTemplateLoader(classTemplateLoader);
        Template template = CONFIGURATION.getTemplate(metaData.getTemplateName());
        String absolutePath = new File("").getAbsolutePath();
        File file = createFile(absolutePath + metaData.getFileName());
        OutputStream fos = new FileOutputStream(file, metaData.isCover());
        Writer out = new OutputStreamWriter(fos);
        template.process(metaData.getData(), out);
        out.flush();
        out.close();
    }

    private static File createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        return file;
    }

}
