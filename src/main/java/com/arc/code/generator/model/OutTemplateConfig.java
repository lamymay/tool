package com.arc.code.generator.model;

import com.arc.code.generator.model.domain.TableMeta;

/**
 * data template outFile
 *
 * @author may
 * @since 2021/4/23 19:42
 */
@Deprecated
public class OutTemplateConfig implements TemplateOutConfig {

    /**
     * 合成模板需要使用的数据
     */
    @Deprecated
    private TableMeta meta;

    private Object data;

    private String templateFileName;

    private String outputFileFullName;

    private boolean success;

    public OutTemplateConfig(String templateFileName, String outputFileFullName) {
        this.templateFileName = templateFileName;
        this.outputFileFullName = outputFileFullName;
    }

    public OutTemplateConfig(String templateFileName, String outputFileFullName, Object data) {
        this.templateFileName = templateFileName;
        this.outputFileFullName = outputFileFullName;
        this.data = data;
    }

    public OutTemplateConfig() {

    }

    public TableMeta getMeta() {
        return meta;
    }

    public void setMeta(TableMeta meta) {
        this.meta = meta;
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    @Override
    public String getOutputFileFullName() {
        return outputFileFullName;
    }

    public void setOutputFileFullName(String outputFileFullName) {
        this.outputFileFullName = outputFileFullName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

