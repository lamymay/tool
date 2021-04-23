package com.arc.code.generator.model;

import com.arc.code.generator.model.domain.meta.TableMeta;
import lombok.Data;

/**
 * @author may
 * @since 2021/4/23 17:37
 */
@Data
//@Deprecated
public class TemplateValue {

    /**
     * 表的元元素描述数据
     */
    private TableMeta meta;

    private String className;

    private String lowerCaseFirstWordClassName;


    private String mapperXmlOutputFileName = className + "Mapper.xml";
    private String modelOutputFileName = ".java";
    private String mapperInterfaceOutputFileName = className + "Mapper.java";

    private String serviceOutputFileName = className + "Service.java";
    private String serviceImplOutputFileName = className + "ServiceImpl.java";

    private String controllerOutputFileName = className + "Controller.java";

    private String requestOutputFileName = className + "Request.java";
    private String responseOutputFileName = className + "Response.java";



    private static final String modelFtl = "model.ftl";
    private static final String requestFtl = "request.ftl";
    private static final String responseFtl = "response.ftl";

    private static final String controllerFtl = "controller.ftl";
    private static final String serviceImplFtl = "serviceImpl.ftl";
    private static final String serviceFtl = "service.ftl";

    private static final String mapperInterfaceFtl = "mapperInterface.ftl";
    private static final String mapperXmlFtl = "mapperXml.ftl";
    private static final String JAVA_FILE_SUFFIX = ".java";

}

