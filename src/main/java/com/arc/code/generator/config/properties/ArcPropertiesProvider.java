package com.arc.code.generator.config.properties;

import com.arc.code.generator.config.properties.auto.DataSourceProperties;
import com.arc.code.generator.config.properties.auto.DatabaseProperties;
import com.arc.code.generator.config.properties.auto.ProjectProperties;

/**
 * @author May
 * @since 2020/1/6 11:05
 */
public interface ArcPropertiesProvider {

    //数据库 四要素： 地址、账号、密码、驱动
    String getDataSourceUrl();

    String getDataSourceUsername();

    String getDataSourcePassword();

    String getDataSourceDriverClassName();

    //数据库的信息：数据库名称、表名称、表别名

    /**
     * 数据库名称
     *
     * @return 数据库名称
     */
    String getSchemaName();

    /**
     * 表名称
     *
     * @return 表名称
     */
    String getTableName();

    /**
     * 表别名
     *
     * @return 表别名
     */
    String getTableAlias();

    //项目的一些配置
    String getProjectRootNamespace();

    String getProjectMapperNamespace();

    String getProjectModelNamespace();

    String getProjectServiceNamespace();

    String getProjectServiceImplNamespace();

    String getProjectControllerNamespace();

    String getProjectOutputFolder();

    /**
     * 是否仅仅输出mapper接口与xml
     * 默认false=即输出：model、mapper、interface、service、controller
     *
     * @return
     */
    boolean getOnlyModelMapperAndXml();


    /**
     * 是否仅仅使用行尾注释而禁止多行注释
     * 默认 false =即输出文件注释规范为：多行注释而非单行注释
     *
     * @return
     */
    boolean getOnlyEnableEndOfLineCommentAndDisableMultilineComment();

    /**
     * 默认输出文件夹
     *
     * @return
     */
    default String getOutput() {
        return getProjectOutputFolder();
    }

    default DataSourceProperties getDataSourceProperties() {
        String url = getDataSourceUrl();
        String username = getDataSourceUsername();
        String password = getDataSourcePassword();
        String driverClassName = getDataSourceDriverClassName();
        return new DataSourceProperties(url, username, password, driverClassName);
    }

    //@Override
    default DatabaseProperties getDatabaseProperties() {
        String schemaName = getSchemaName();
        String tableName = getTableName();
        String tableAlias = getTableAlias();
        return new DatabaseProperties(schemaName, tableName, tableAlias);
    }

    //@Override
    default ProjectProperties getProjectProperties() {
        String rootNamespace = getProjectRootNamespace();
        String mapperNamespace = getProjectMapperNamespace();
        String modelNamespace = getProjectModelNamespace();
        String serviceNamespace = getProjectServiceNamespace();
        String serviceImplNamespace = getProjectServiceImplNamespace();
        String controllerNamespace = getProjectControllerNamespace();
        String outputFolder = getProjectOutputFolder();
        return new ProjectProperties(rootNamespace, mapperNamespace, modelNamespace, serviceNamespace, serviceImplNamespace, controllerNamespace, outputFolder);
    }


}
