//package com.arc.code.generator.config.properties.impl;
//
//import com.arc.code.generator.config.properties.ArcPropertiesProvider;
//import com.arc.code.generator.config.properties.auto.ArcCodeGeneratorPropertiesProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * 通过Spring自动配置来设置上下文配置
// *
// * @author May
// * @since 2020/1/6 11:19
// */
//@Component("arcPropertiesProviderImpl1")
//public class ArcPropertiesProviderImpl1 implements ArcPropertiesProvider {
//
//    /**
//     * 配上数据源、数据表、项目等一些参数
//     */
//    @Autowired
//    private ArcCodeGeneratorPropertiesProvider arcCodeGeneratorPropertiesProvider;
//
//
//    @Override
//    public String getDataSourceUrl() {
//        return arcCodeGeneratorPropertiesProvider.getDataSource().getUrl();
//    }
//
//    @Override
//    public String getDataSourceUsername() {
//        return arcCodeGeneratorPropertiesProvider.getDataSource().getUsername();
//    }
//
//    @Override
//    public String getDataSourcePassword() {
//        return arcCodeGeneratorPropertiesProvider.getDataSource().getPassword();
//    }
//
//    @Override
//    public String getDataSourceDriverClassName() {
//        return arcCodeGeneratorPropertiesProvider.getDataSource().getDriverClassName();
//    }
//
//    @Override
//    public String getSchemaName() {
//        return arcCodeGeneratorPropertiesProvider.getDatabase().getSchemaName();
//    }
//
//    @Override
//    public String getTableName() {
//        return arcCodeGeneratorPropertiesProvider.getDatabase().getTableName();
//    }
//
//    @Override
//    public String getTableAlias() {
//        return arcCodeGeneratorPropertiesProvider.getDatabase().getTableAlias();
//    }
//
//    @Override
//    public String getProjectRootNamespace() {
//        return arcCodeGeneratorPropertiesProvider.getProject().getRootNamespace();
//    }
//
//    @Override
//    public String getProjectMapperNamespace() {
//        return arcCodeGeneratorPropertiesProvider.getProject().getMapperNamespace();
//    }
//
//    @Override
//    public String getProjectModelNamespace() {
//        return arcCodeGeneratorPropertiesProvider.getProject().getModelNamespace();
//    }
//
//    @Override
//    public String getProjectServiceNamespace() {
//        return arcCodeGeneratorPropertiesProvider.getProject().getServiceNamespace();
//    }
//
//    @Override
//    public String getProjectServiceImplNamespace() {
//        return arcCodeGeneratorPropertiesProvider.getProject().getServiceImplNamespace();
//    }
//
//    @Override
//    public String getProjectControllerNamespace() {
//        return arcCodeGeneratorPropertiesProvider.getProject().getControllerNamespace();
//    }
//
//    @Override
//    public String getProjectOutputFolder() {
//        return arcCodeGeneratorPropertiesProvider.getProject().getOutputFolder();
//    }
//
//    @Override
//    public boolean getOnlyModelMapperAndXml() {
//        return arcCodeGeneratorPropertiesProvider.getProject().isOnlyModelMapperAndXml();
//    }
//
//    @Override
//    public boolean getOnlyEnableEndOfLineCommentAndDisableMultilineComment() {
//        return arcCodeGeneratorPropertiesProvider.getProject().isOnlyEnableEndOfLineCommentAndDisableMultilineComment();
//    }
//}
