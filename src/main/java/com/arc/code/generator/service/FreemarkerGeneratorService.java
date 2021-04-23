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


    // 创建 model 文件
    // 创建 mapper接口文件
    // 创建 mapper.xml文件
    // 创建 service 文件
    // 创建 service.impl 文件
    // 创建 controller 文件

    /**
     * 真正执行数据与模板合成的方法
     *
     * @param propertiesProvider 上下文配置
     * @return ArcPropertiesProvider
     */
    ArcPropertiesProvider executeByContext(ArcPropertiesProvider propertiesProvider);


}
