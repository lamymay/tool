package com.arc.code.generator.model;

import com.arc.code.generator.model.domain.TableMeta;

import java.util.Date;

/**
 * @author may
 * @since 2021/4/23 17:37
 */
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

    public TableMeta getMeta() {
        return meta;
    }

    public void setMeta(TableMeta meta) {
        this.meta = meta;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getJavaPackage() {
        return javaPackage;
    }

    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
    }

    public String getRootNamespace() {
        return rootNamespace;
    }

    public void setRootNamespace(String rootNamespace) {
        this.rootNamespace = rootNamespace;
    }

    public String getLowerCaseFirstWordClassName() {
        return lowerCaseFirstWordClassName;
    }

    public void setLowerCaseFirstWordClassName(String lowerCaseFirstWordClassName) {
        this.lowerCaseFirstWordClassName = lowerCaseFirstWordClassName;
    }

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

