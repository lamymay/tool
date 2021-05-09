package com.arc.code.generator.config.swagger2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: yechao
 * @date: 2018/10/112 11:17
 */
@EnableSwagger2
@Configuration
@EnableConfigurationProperties
public class Swagger2ApiConfiguration  {

    @Autowired
    private Swagger2Properties prop;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(prop.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(prop.getTitle())
                .description(prop.getDescription())
                .termsOfServiceUrl(prop.getTermsOfServiceUrl())
                .contact(new Contact(prop.getContactName(), prop.getContactUrl(), prop.getContactEmail()))
                .version(prop.getVersion())
                .build();
    }

    /**
     * 对于yml配置文件的的属性做映射
     * 目的为了配置swagger2
     */
    @Component
    @ConfigurationProperties(prefix = "msf.swagger2")
    public static class Swagger2Properties {

        private String basePackage="com";

        private String title="文档";

        private String description="默认配置下扫描到的接口";

        private String version="0.0.0";

        private String termsOfServiceUrl="/";

        private String contactName="unknown";

        private String contactUrl="unknown";

        private String contactEmail="2320158601@qq.com";

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }
    }

    public Swagger2Properties getProp() {
        return prop;
    }

    public void setProp(Swagger2Properties prop) {
        this.prop = prop;
    }
}
