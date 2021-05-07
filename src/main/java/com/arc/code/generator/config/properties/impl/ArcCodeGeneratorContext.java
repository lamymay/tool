package com.arc.code.generator.config.properties.impl;

import com.alibaba.fastjson.JSON;
import com.arc.code.generator.model.ProjectConfig;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 代码生成器环境上下文
 *
 * @author may
 * @since 2021/4/16 18:19
 */
@Data
public final class ArcCodeGeneratorContext {

    private String url; //数据库连接url
    private String user;  //数据库账号
    private String password; //数据库密码
    private String driverClassName; //驱动名称

    private String schemaName;//schemaName(数据库名)【必要】
    private String tableName;//表名
    private String removePrefix;//数据库表的前缀

    private String tableAlias;//数据库表在mapper的sql中的别名
    private String author;//"@author" 需要指定,缺省情况下获取机器当前用户

    /**
     * 项目维度的配置可缺省?
     */
    private ProjectConfig projectConfig;


    // ****************  文件输出相关配置 ****************

    @Deprecated
    private String output = File.separator + "code_output_";// //T:\data\log\

    //输出配置
    private String rootNamespace = "com.arc.zero";

    /**
     * 代码生成方案
     */
    private Integer generateType;

    /**
     * 输出方案: 0=输出到同一个输出文件夹(默认) 1=输出到新的MAVEN项目文件夹
     */
    private Integer outputType;


    /**
     * 要多行注释还单行注释
     * multiline comment 多行注释
     * End-of-line comment  行尾注释
     */
    private boolean commentFormatAsEndOfLine = false;//End-of-line comment  行尾注释

    @Deprecated
    private boolean onlyEnableEndOfLineCommentAndDisableMultilineComment = false;

    private boolean success;

    @Deprecated
    private boolean useProjectDefaultDataSource;// true=使用项目默认的数据源/false=使用用户指定的数据源

    public String getAuthor() {
        if (this.author == null) {
            author = System.getProperty("user.name");
        }
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static ArcCodeGeneratorContext getArcPropertiesProvider() {

        // 参数配置
        com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext configContext = new com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext();
        configContext.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8");
        configContext.setUser("root");
        configContext.setPassword("admin");
        configContext.setDriverClassName("com.mysql.cj.jdbc.Driver");

        //数据库名 建议指定若不指定使用默认数据JDBC url中的数据库
        configContext.setGenerateType(91);
        configContext.setOutputType(1);

        configContext.setSchemaName("bu_oms");
        configContext.setTableName("oms_inquiry_order");
        configContext.setRemovePrefix("oms_");
//        configContext.setTableAlias("click");

        configContext.setAuthor("叶超");

        ProjectConfig classFullName = new ProjectConfig("com.arc.app");
        configContext.setRootNamespace(classFullName.getRootNamespace());
        classFullName.setProjectName("code");

        configContext.setProjectConfig(classFullName);
        configContext.setCommentFormatAsEndOfLine(true);

        //        configContext.setMapperNamespace("com.demo.mapper");
        //        configContext.setServiceNamespace("com.demo.service");
        //        configContext.setServiceImplNamespace("com.demo.service.impl");
        //        configContext.setControllerNamespace("com.demo.service.impl");

        configContext.setOutput("D:\\free");


        System.out.println(JSON.toJSONString(configContext));
        return configContext;

    }

    public void setClassNameIfNotConfig(String className) {
        ProjectConfig projectConfig = this.getProjectConfig();
        if (projectConfig == null) {
            projectConfig = new ProjectConfig();
        }

        if (StringUtils.isBlank(projectConfig.getClassName())) {
            projectConfig.setClassName(className);
        }
    }

}
