package com.arc.code.generator.service;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;

import java.util.Map;

/**
 * 创建代码的服务
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/10/3 8:01
 */
public interface FreemarkerGeneratorService {

    /**
     * @param propertiesProvider
     * @return
     */
    Map<String, Object> execute(ArcPropertiesProvider propertiesProvider);

    /**
     * 真正执行数据与模板合成的方法
     *
     * @param parameterMap parameterMap
     * @return Map
     */
    Map<String, Object> executeByContext(Map<String, Object> parameterMap);


    //todo 返回值需要做优化
//    ParameterModel executeByMap(ParameterModel parameter);

}
