package com.arc.code.generator.model;

import lombok.Data;

/**
 * data template outFile
 *
 * @author may
 * @since 2021/4/23 19:42
 */
@Data
public class OutTemplateConfig {


    /**
     * 合成模板需要使用的数据
     */
    private TemplateData data;

//    /**
//     * 模板
//     */
//    private Template template;

//    /**
//     * 输出文件
//     */
//    private File outputFile;

    private String templateName;

     private String outputFileFullName;

    private boolean success;

}

/*
    private Map<String, Object> data;

    {
        Map<Object, Object> parameterMap = new HashMap<>();
        parameterMap.put("meta", tableMeta);
        parameterMap.put("className", tableMeta.getClassName());
        parameterMap.put("lowerCaseFirstWordClassName", tableMeta.getLowerCaseFirstWordClassName());
        parameterMap.put(TableMeta.class.getName(), tableMeta);
    }

 */

