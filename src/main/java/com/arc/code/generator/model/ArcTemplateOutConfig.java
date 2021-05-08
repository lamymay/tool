package com.arc.code.generator.model;

import lombok.Data;

/**
 * data template outFile
 *
 * @author may
 * @since 2021/4/23 19:42
 */
@Data
public class ArcTemplateOutConfig implements TemplateOutConfig {

    /**
     * 合成模板需要使用的数据
     */
    private Object data;

    private String templateFileName;

    private String outputFileFullName;

    private boolean success;

    public ArcTemplateOutConfig(String templateFileName, String outputFileFullName) {
        this.templateFileName = templateFileName;
        this.outputFileFullName = outputFileFullName;
    }

    public ArcTemplateOutConfig(String templateFileName, String outputFileFullName, Object data) {
        this.templateFileName = templateFileName;
        this.outputFileFullName = outputFileFullName;
        this.data = data;
    }

    public ArcTemplateOutConfig() {

    }
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

