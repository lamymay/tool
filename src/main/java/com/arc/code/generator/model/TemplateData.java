package com.arc.code.generator.model;

import com.arc.code.generator.model.domain.TableMeta;
import lombok.Data;

import java.util.Date;

/**
 * @author may
 * @since 2021/4/23 17:37
 */
@Data
@Deprecated
public class TemplateData {

    /**
     * 表的元元素描述数据
     */
    @Deprecated
    private TableMeta meta;

    private String className;

    private String tableName;

    private Date createTime=new Date();

    /**
     * 表注释
     */
    private String tableComment;

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

