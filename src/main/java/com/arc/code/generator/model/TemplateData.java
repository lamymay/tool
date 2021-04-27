package com.arc.code.generator.model;

import com.arc.code.generator.model.domain.meta.TableMeta;
import lombok.Data;

/**
 * @author may
 * @since 2021/4/23 17:37
 */
@Data
//@Deprecated
public class TemplateData {

    /**
     * 表的元元素描述数据
     */
    private TableMeta meta;

    private String className;

    private String tableName;
    private String tableAlias;
    private String javaPackage;
    private String rootNamespace;

    private String lowerCaseFirstWordClassName;


//    private String mapperXmlOutputFileName = className + "Mapper.xml";
//    private String modelOutputFileName = className+".java";
//    private String mapperInterfaceOutputFileName = className + "Mapper.java";
//
//    private String serviceOutputFileName = className + "Service.java";
//    private String serviceImplOutputFileName = className + "ServiceImpl.java";
//
//    private String controllerOutputFileName = className + "Controller.java";
//
//    private String requestOutputFileName = className + "Request.java";
//    private String responseOutputFileName = className + "Response.java";





}

