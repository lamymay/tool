package com.arc.code.generator.config.properties.impl;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import lombok.Data;

import java.io.File;

/**
 * 代码生成器环境上下文
 *
 * @author may
 * @since 2021/4/16 18:19
 */
@Data
public final class ArcCodeGeneratorContext implements ArcPropertiesProvider {

    private String url; //数据库连接url
    private String username;  //数据库账号
    private String password; //数据库密码
    private String driverClassName; //驱动名称

    private String schemaName;//schemaName(数据库名)【必要】
    private String tableName;//表名
    private String removePrefix;//数据库表的前缀

    private String tableAlias;//数据库表在mapper的sql中的别名
    private String author;//"@author" 需要指定,缺省情况下获取机器当前用户

    private String output = File.separator + "output_";// //T:\data\log\

    //输出配置
    private String rootNamespace = "com.arc.zero";
    private String mapperNamespace = "com.arc.zero.mapper.shop";
    private String modelNamespace = "com.arc.core.model.domain.shop";
    private String serviceNamespace = "com.arc.core.model.domain.shop";
    private String serviceImplNamespace = "com.arc.core.model.domain.shop";
    private String controllerNamespace = "com.arc.core.model.domain.shop";

    Integer generateType;

    @Override
    public Integer getGenerateType() {
        return generateType;
    }

    @Override
    public void setGenerateType(Integer generateType) {
        this.generateType = generateType;
    }

    @Override
    public String getOutput() {
        return output;
    }

    @Override
    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String getRemovePrefix() {
        return removePrefix;
    }

    @Override
    public void setRemovePrefix(String removePrefix) {
        this.removePrefix = removePrefix;
    }

    /**
     * 要多行注释还单行注释
     * multiline comment 多行注释
     * End-of-line comment  行尾注释
     */
    private boolean commentFormatAsEndOfLine = false;//End-of-line comment  行尾注释

    @Deprecated
    private boolean onlyEnableEndOfLineCommentAndDisableMultilineComment = false;

    private boolean success;

    private boolean useProjectDefaultDataSource;// true=使用项目默认的数据源/false=使用用户指定的数据源

    private boolean scanAllTable = false;// true=使用项目默认的数据源/false=使用用户指定的数据源

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public boolean isUseProjectDefaultDataSource() {
        return useProjectDefaultDataSource;
    }

    @Override
    public void setUseProjectDefaultDataSource(boolean useProjectDefaultDataSource) {
        this.useProjectDefaultDataSource = useProjectDefaultDataSource;
    }

    @Override
    public boolean isScanAllTable() {
        return scanAllTable;
    }

    @Override
    public void setScanAllTable(boolean scanAllTable) {
        this.scanAllTable = scanAllTable;
    }

    @Override
    public String getDataSourceUrl() {
        return url;
    }

    @Override
    public String getDataSourceUsername() {
        return username;
    }

    @Override
    public String getDataSourcePassword() {
        return password;
    }

    @Override
    public String getDataSourceDriverClassName() {
        return driverClassName;
    }

    @Override
    public String getRootNamespace() {
        return rootNamespace;
    }

    @Override
    public String getMapperNamespace() {
        return mapperNamespace;
    }

    @Override
    public String getProjectModelNamespace() {
        return modelNamespace;
    }

    @Override
    public String getProjectServiceNamespace() {
        return serviceNamespace;
    }

    @Override
    public String getProjectServiceImplNamespace() {
        return serviceImplNamespace;
    }

    @Override
    public String getProjectControllerNamespace() {
        return controllerNamespace;
    }

    @Override
    public String getProjectOutputFolder() {
        return output;
    }

    @Override
    public boolean getOnlyEnableEndOfLineCommentAndDisableMultilineComment() {
        return commentFormatAsEndOfLine;
    }


    @Override
    public String getAuthor() {
        if (this.author == null) {
            author = System.getProperty("user.name");
        }
        return this.author;
    }


}

//    {
//    数据库链接配置
//
//        //8.0   "com.mysql.cj.jdbc.Driver"
//        url = "jdbc:mysql://122.51.110.127:3306/zero?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8";
//        username = "lamymay";
//        password = "lamymay12345677Z!";
//        driverClassName = "com.mysql.cj.jdbc.Driver";
//
//        //表的一些配置 schemaName(数据库名) tableName(表名) tableAlias(表别名)
//        schemaName = "zero";
//        tableName = "t_we_chat_access_token";
//        tableAlias = "we_chat_access_token";
//    }

