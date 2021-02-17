package com.arc.code.generator.config.properties.auto;

import lombok.*;

/**
 * 配置文件向代码注入
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/5/22 22:17
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProjectProperties {

    private String rootNamespace = "com.arc";
    private String mapperNamespace = "com.arc.mapper.shop";
    private String modelNamespace = "com.arc.core.model.domain.shop";
    private String serviceNamespace;
    private String serviceImplNamespace;
    private String controllerNamespace;
    private String outputFolder = "/outputFolder";
    /**
     *获取tableMate数据时候使用自定义
     */
    private boolean useCustomizeDataSourceByControllerReceived = false;
    private boolean onlyModelMapperAndXml = false;

    /**
     * multiline comment 多行注释
     * End-of-line comment  行尾注释
     */
    private boolean onlyEnableEndOfLineCommentAndDisableMultilineComment = false;

    public ProjectProperties(String rootNamespace, String mapperNamespace, String modelNamespace, String outputFolder) {
        this.rootNamespace = rootNamespace;
        this.mapperNamespace = mapperNamespace;
        this.modelNamespace = modelNamespace;
        this.outputFolder = outputFolder;
    }

    public ProjectProperties(final String rootNamespace, final String mapperNamespace, final String modelNamespace, final String serviceNamespace, final String serviceImplNamespace, final String controllerNamespace, final String outputFolder) {
        this.rootNamespace = rootNamespace;
        this.mapperNamespace = mapperNamespace;
        this.modelNamespace = modelNamespace;
        this.serviceNamespace = serviceNamespace;
        this.serviceImplNamespace = serviceImplNamespace;
        this.controllerNamespace = controllerNamespace;
        this.outputFolder = outputFolder;
    }
}
