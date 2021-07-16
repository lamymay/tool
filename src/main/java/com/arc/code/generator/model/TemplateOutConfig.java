package com.arc.code.generator.model;

/**
 * 模板输出相关的配置
 *
 * @author may
 * @since 2021/5/8 9:42
 */
public interface TemplateOutConfig {


    String getTemplateFileName();

    String getOutputFileFullName();

    @Deprecated
    Object getData();

}
