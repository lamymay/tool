package com.arc.code.generator.model;

import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * 全限定类名称
 *
 * @author may
 * @since 2021/4/30 14:26
 */
public class ProjectConfig {

    private String projectName = "default_test";

    /**
     * 基础包的包名称
     */
    private String basePackage;

    /*
    都可以缺省
    FullyQualifiedClassName全限定类名 = 基础包名 + 子包 + 类名称
    * */

    private String modelName;
    private String modelPackage;

    private String mapperName;
    private String mapperPackage;

    private String serviceName;
    private String servicePackage;

    private String serviceImplName;
    private String serviceImplPackage;


    /**
     * controller类名
     */
    private String controllerName;
    /**
     * controller包名
     */
    private String controllerPackage;

    public ProjectConfig() {

    }

    public ProjectConfig(ArcCodeGeneratorContext arcCodeGeneratorContext) {
        if (arcCodeGeneratorContext == null || arcCodeGeneratorContext.getProjectConfig() == null) {
            throw new RuntimeException("上下文中项目如何输出配置不可缺省,解决:指定项目输出相关参数(基础包包名basePackage,主模型类名称modelName)");
        }
        ProjectConfig projectConfig = arcCodeGeneratorContext.getProjectConfig();

        // 基础包包名
        final String basePackage = projectConfig.getBasePackage();
        this.basePackage = basePackage;
        if (StringUtils.isBlank(basePackage)) {
            throw new RuntimeException("类名称与包根路径不可缺省");
        }

        // 主模型类名称(例如: 处理的是t_user表数据, 此时模型名称modelName默认 = User, 如果你指定了就是按照你指定的名称显示(see 方法 com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext.setClassNameIfNotConfig) )
        final String modelName = projectConfig.getModelName();
        this.modelName = modelName;
        if (StringUtils.isBlank(modelName)) {
            throw new RuntimeException("主模型类名称不可缺省");
        }


        // model
        if (StringUtils.isNotBlank(projectConfig.getModelPackage())) {
            this.modelPackage = projectConfig.getModelPackage();
        } else {
            this.modelPackage = basePackage + ".model";
        }

        // controller
        if (StringUtils.isNotBlank(projectConfig.getControllerName())) {
            this.controllerName = projectConfig.getControllerName();
        } else {
            this.controllerName = modelName + "Controller";
        }
        if (StringUtils.isNotBlank(projectConfig.getControllerPackage())) {
            this.controllerPackage = projectConfig.getControllerPackage();
        } else {
            this.modelPackage = basePackage + ".controller";
        }

        // service
        if (StringUtils.isNotBlank(projectConfig.getServiceName())) {
            this.serviceName = projectConfig.getServiceName();
        } else {
            this.serviceName = modelName + "Service";
        }
        if (StringUtils.isNotBlank(projectConfig.getServicePackage())) {
            this.servicePackage = projectConfig.getServicePackage();
        } else {
            this.modelPackage = basePackage + ".service";
        }

        // serviceImpl
        if (StringUtils.isNotBlank(projectConfig.getServiceImplName())) {
            this.serviceImplName = projectConfig.getServiceImplName();
        } else {
            this.serviceImplName = modelName + "ServiceImpl";
        }
        if (StringUtils.isNotBlank(projectConfig.getServiceImplPackage())) {
            this.serviceImplPackage = projectConfig.getServiceImplPackage();
        } else {
            this.modelPackage = basePackage + ".service.impl";
        }

        // mapper
        if (StringUtils.isNotBlank(projectConfig.getMapperName())) {
            this.mapperName = projectConfig.getMapperName();
        } else {
            this.mapperName = modelName + "Mapper";
        }
        if (StringUtils.isNotBlank(projectConfig.getMapperPackage())) {
            this.mapperPackage = projectConfig.getMapperPackage();
        } else {
            this.modelPackage = basePackage + ".mapper";
        }


    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }
}
