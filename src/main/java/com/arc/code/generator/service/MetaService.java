package com.arc.code.generator.service;

import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.model.domain.TableMeta;

import java.util.List;

/**
 * 表yuan元数据获取
 *
 * @author may
 * @since 2021/4/23 11:01
 */
public interface MetaService {

    /**
     * 从db中获取表元元素模型
     *
     * @param url             url
     * @param user            user
     * @param password        password
     * @param driverClassName driverClassName
     * @param schemaName      库名称
     * @param tableName       表名称
     * @return 表模型
     */
    TableMeta selectTableMateByJDBC(String url, String user, String password, String driverClassName,
                                    String schemaName, String tableName);

    /**
     * 从db中获取表元元素模型
     *
     * @param generatorContext 代码生成器环境上下文
     * @return 表模型
     */
    TableMeta selectTableMateByJDBC(ArcCodeGeneratorContext generatorContext);

    /**
     * 从db中获取表的元数据 [全表的]
     *
     * @param generatorContext 代码生成器环境上下文
     * @return List
     */
    List<TableMeta> selectListOptimization(ArcCodeGeneratorContext generatorContext);


    /**
     * 通过一批sql构造一批表元元素模型
     *
     * @param createTableSQLList 建表SQL的List
     * @return 表的模型
     */
    List<TableMeta> listTableMateListBySQLStringList(List<String> createTableSQLList);

    /**
     * 通过一批sql构造一批表的模型
     *
     * @param createTableSQLStrings 建表SQL,多个建表SLQ时候采用以英文分号分隔
     * @return 表的模型
     */
    List<TableMeta> listTableMateListBySQLsString(String createTableSQLStrings);

    /**
     * 通过建表SQL构造一个表的模型
     *
     * @param sql 建表SQL
     * @return 表元数据
     */
    TableMeta getTableMateBySQLString(String sql);


}
