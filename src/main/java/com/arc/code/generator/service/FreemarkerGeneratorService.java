package com.arc.code.generator.service;


import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;

import java.io.File;

/**
 * 创建代码的服务
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/10/3 8:01
 */
public interface FreemarkerGeneratorService {

    /**
     * 真正执行数据与模板合成的方法
     *
     * @param propertiesProvider 上下文配置
     * @return ArcPropertiesProvider
     */
    ArcCodeGeneratorContext processByContext(ArcCodeGeneratorContext propertiesProvider);


    void outputFile(File outputFile, String templateName, Object data);
}


/*
此方法是更具指定的策略 & 配置 输出
     创建 model 文件
     创建 mapper接口文件
     创建 mapper.xml文件
     创建 service 文件
     创建 service.impl 文件
     创建 controller 文件


1环境上下文模块(1项目默认数据源/ 2导出配置--2.1注释格式--2.2创建的文件格式--2.3输出Zip/输出一个测试项目--2.4是否使用默认数据源--2.5重复文件追加or覆盖)
 |
 |--web传入
 |--项目properties
 |--Java中指定
 |--读取指定配置文件模式
 |--读取db


2db交互模块
 |
 |--by jdbc/ORM框架


3数据预处理模块
 |
 |--配置参数与扫描到数据


4模板合成模块
 |
 |--预设模版
 |--自定义模版

5文件输出模块
 |
 |--合成输出文件
 |--输出文件清理缓存
 |--性能与安全统计模块(谁什么时候生成过什么,耗时多久,有无报错,)-->统计用户使用习惯,为了迭代做准准备



*/
