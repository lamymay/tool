package com.arc.test.freemarker;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.model.domain.meta.TableMeta;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 叶超
 * @since 2019/10/4 10:31
 */
@Slf4j
public class Handler {

    @Autowired
    private ArcPropertiesProvider generatorProperties;

    @Autowired
    private Configuration configuration;

    public Map<String, Object> dataModel = new HashMap<>(40);

    private static final String JAVA_FILE_SUFFIX = ".java";

    private static final String MAPPER_FILE_SUFFIX = ".xml";
    private static final String output = "C:\\Users\\Z\\Desktop\\Za\\output\\";

    // 创建 generate java model class
    private void generateModel(TableMeta meta) throws Exception {
        dataModel.put("meta", meta);
        dataModel.put("javaPackage", generatorProperties.getProjectProperties().getRootNamespace());

        String className = meta.getClassName();
        String fileName = (output + File.separator + className + JAVA_FILE_SUFFIX);

        File newFile = new File(fileName);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        Template template = configuration.getTemplate("model.ftl");
        log.info("Use template file: {}. ", template.getName());
        template.process(dataModel, new FileWriter(newFile));
    }

    // generate mybatis mapper xml
    private void generateMapper(TableMeta meta) throws Exception {
        Template template = configuration.getTemplate("mapperInterface.ftl");
        log.info("Use template file: {}. ", template.getName());

        dataModel.put("meta", meta);
        dataModel.put("javaPackage", generatorProperties.getProjectProperties().getRootNamespace());
        dataModel.put("columnPrefix", generatorProperties.getDatabaseProperties().getTableAlias());

        String mapperFileName = (meta.getMapperName() + MAPPER_FILE_SUFFIX);

        File mapperFile = new File(mapperFileName);
        if (!mapperFile.exists()) {
            mapperFile.createNewFile();
        }
        template.process(dataModel, new FileWriter(mapperFile));
    }
}
