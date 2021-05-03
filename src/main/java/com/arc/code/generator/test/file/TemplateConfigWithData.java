package com.arc.code.generator.test.file;

import lombok.Data;

/**
 * @author may
 * @since 2021/5/4 0:01
 */
@Data
public class TemplateConfigWithData {

    /**
     * 模板文件名称
     */
    private String templateFileName;

    /**
     * 输出文件名称
     */
    private String outputFileName;

    /**
     * 模板文件合成需要的数据
     */
    private Object templateData;

    public TemplateConfigWithData(String templateFileName, String outputFileName, Object templateData) {
        this.templateFileName = templateFileName;
        this.outputFileName = outputFileName;
        this.templateData = templateData;
    }
}
