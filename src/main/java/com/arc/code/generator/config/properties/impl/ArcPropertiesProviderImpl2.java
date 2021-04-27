//package com.arc.code.generator.config.properties.impl;
//
//import com.arc.code.generator.config.properties.ArcPropertiesProvider;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//
///**
// * 硬编码
// *
// * @author 叶超
// * @since 2020/1/6 11:19
// */
//@Deprecated
//@Component("arcPropertiesProviderImpl2")
//public class ArcPropertiesProviderImpl2 implements ArcPropertiesProvider {
//
//    //数据库链接配置
//    String url = "jdbc:mysql://122.51.110.127:3306/zero?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8";
//    String driverClassName = "com.mysql.cj.jdbc.Driver";
//    String username = "lamymay";
//    String password = "lamymay12345677Z!";
//
//
//    //表的一些配置 schemaName(数据库名) tableName(表名) tableAlias(表别名)
//    String schemaName = "zero";
//    String tableName = "t_we_chat_access_token";
//    String tableAlias = "we_chat_access_token";
//
//
//    //输出配置
//    String rootNamespace = "com.arc.zero";
//    String mapperNamespace = "com.arc.zero.mapper.shop";
//    String modelNamespace = "com.arc.core.model.domain.shop";
//    String serviceNamespace = "com.arc.core.model.domain.shop";
//    String serviceImplNamespace = "com.arc.core.model.domain.shop";
//    String controllerNamespace = "com.arc.core.model.domain.shop";
//
//    //T:\data\log\
//    String outputFolder = File.separator + "output_1111";
//
//
//    @Override
//    public String getDataSourceUrl() {
//        return url;
//    }
//
//    @Override
//    public String getDataSourceUsername() {
//        return username;
//    }
//
//    @Override
//    public String getDataSourcePassword() {
//        return password;
//    }
//
//    @Override
//    public String getDataSourceDriverClassName() {
//        return driverClassName;
//    }
//
//    @Override
//    public String getSchemaName() {
//        return schemaName;
//    }
//
//    @Override
//    public String getTableName() {
//        return tableName;
//    }
//
//    @Override
//    public String getTableAlias() {
//        return tableAlias;
//    }
//
//    @Override
//    public String getProjectRootNamespace() {
//        return rootNamespace;
//    }
//
//    @Override
//    public String getProjectMapperNamespace() {
//        return mapperNamespace;
//    }
//
//    @Override
//    public String getProjectModelNamespace() {
//        return modelNamespace;
//    }
//
//    @Override
//    public String getProjectServiceNamespace() {
//        return serviceNamespace;
//    }
//
//    @Override
//    public String getProjectServiceImplNamespace() {
//        return serviceImplNamespace;
//    }
//
//    @Override
//    public String getProjectControllerNamespace() {
//        return controllerNamespace;
//    }
//
//    @Override
//    public String getProjectOutputFolder() {
//        return outputFolder;
//    }
//
//    @Override
//    public boolean getOnlyModelMapperAndXml() {
//        return false;
//    }
//
//    @Override
//    public boolean getOnlyEnableEndOfLineCommentAndDisableMultilineComment() {
//        return false;
//    }
//}
