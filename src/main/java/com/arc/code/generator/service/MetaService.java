package com.arc.code.generator.service;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
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
     * 获取表的元数据
     *
     * @param arcPropertiesProvider       代码生成器环境上下文
     * @param useProjectDefaultDataSource 是否使用默认数据源
     * @return
     */
    TableMeta selectTableMateOptimization(ArcPropertiesProvider arcPropertiesProvider, boolean useProjectDefaultDataSource);

    /**
     * 查一个库中的全部表并解析出来
     *
     * @param arcPropertiesProvider       ArcPropertiesProvider
     * @param useProjectDefaultDataSource useProjectDefaultDataSource
     * @return List
     */
    List<TableMeta> selectTableMateListOptimization(ArcPropertiesProvider arcPropertiesProvider, boolean useProjectDefaultDataSource);

//    TableMeta selectTableMateByMybatis(ArcPropertiesProvider arcPropertiesProvider);

    TableMeta selectTableMateByJDBC(ArcPropertiesProvider propertiesProvider);
}
