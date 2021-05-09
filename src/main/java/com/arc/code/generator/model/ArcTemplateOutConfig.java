package com.arc.code.generator.model;


/**
 * data template outFile
 *
 * @author may
 * @since 2021/4/23 19:42
 */
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
