package com.arc.code.generator.model;

import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 全限定类名称
 *
 * @author may
 * @since 2021/4/30 14:26
 */
@Data
public class ClassFullName {

    private String className;

    private String rootNamespace;

    private String modelNamespace;
    private String mapperNamespace;
    private String serviceNamespace;
    private String serviceImplNamespace;
    private String controllerNamespace;

    public ClassFullName(String className, ArcCodeGeneratorContext arcCodeGeneratorContext) {

        if (arcCodeGeneratorContext == null || StringUtils.isBlank(className) || StringUtils.isBlank(arcCodeGeneratorContext.getRootNamespace())) {
            throw new RuntimeException("类名称与包根路径不可缺省");
        }

        this.className = className;
        this.rootNamespace = arcCodeGeneratorContext.getRootNamespace();

        // 传入参数是否指定每个接口的类路径
        ClassFullName defineClassFullName = arcCodeGeneratorContext.getClassFullName();

        if (defineClassFullName != null && StringUtils.isNotBlank(defineClassFullName.getModelNamespace())) {
            this.modelNamespace = defineClassFullName.getModelNamespace();
        } else {
            this.modelNamespace = rootNamespace + className;
        }
        if (defineClassFullName != null && StringUtils.isNotBlank(defineClassFullName.getMapperNamespace())) {
            this.mapperNamespace = defineClassFullName.getMapperNamespace() + "Mapper";
        } else {
            this.mapperNamespace = rootNamespace + "Mapper";
        }
        if (defineClassFullName != null && StringUtils.isNotBlank(defineClassFullName.getServiceNamespace())) {
            this.serviceNamespace = defineClassFullName.getServiceNamespace() + "Service";
        } else {
            this.serviceNamespace = rootNamespace + className + "Service";
        }
        if (defineClassFullName != null && StringUtils.isNotBlank(defineClassFullName.getServiceImplNamespace())) {
            this.serviceImplNamespace = defineClassFullName.getServiceImplNamespace() + "ServiceImpl";
        } else {
            this.serviceImplNamespace = rootNamespace + className + "ServiceImpl";
        }
        if (defineClassFullName != null && StringUtils.isNotBlank(defineClassFullName.getControllerNamespace())) {
            this.controllerNamespace = defineClassFullName.getControllerNamespace() + "Controller";
        } else {
            this.controllerNamespace = rootNamespace + className + "Controller";
        }
    }


    public String getMapperNamespace() {
        if (StringUtils.isNotBlank(mapperNamespace)) {
            return mapperNamespace;
        }
        return rootNamespace + "Mapper";
    }

}
