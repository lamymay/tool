package com.arc.code.generator.model;

import freemarker.template.Template;
import lombok.Data;

import java.io.File;
import java.util.Map;

/**
 * @author may
 * @since 2021/4/23 19:42
 */
@Data
public class OutTemplateConfig {

    // data template outFile

    /**
     *
     */
    //private Map<String, Object> data;
        private TemplateValue data;

//    /**
//     * 模板文件?
//     */
//    @Deprecated
//    private String templateName;

    /**
     * 模板
     */
    private Template template;


    /**
     * 输出文件
     */
    private File outputFile;

}
//        Map<Object, Object> parameterMap = new HashMap<>();
//        parameterMap.put("meta", tableMeta);
//        parameterMap.put("className", tableMeta.getClassName());
//        parameterMap.put("lowerCaseFirstWordClassName", tableMeta.getLowerCaseFirstWordClassName());
//        parameterMap.put(TableMeta.class.getName(), tableMeta);