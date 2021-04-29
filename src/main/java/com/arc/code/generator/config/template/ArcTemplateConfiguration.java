package com.arc.code.generator.config.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * freemarker 配置类
 *
 * @author lamyamay
 * @since 2019/10/25 22:17
 */
@Slf4j
@Configuration
public class ArcTemplateConfiguration {

    //结合使用注解@ConditionalOnMissingClass和@Bean,可以仅当某些类不存在于 classpath 上时候才创建某个Bean：
    //@ConditionalOnMissingClass(value={"com.sample.Dummy","com.sample.Dum"})
    @Bean("freemarkerConfiguration")
    @ConditionalOnMissingClass(value = {"org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer"})
    public freemarker.template.Configuration freemarkerConfiguration() throws Exception {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:templates");
        bean.setDefaultEncoding("UTF-8");
        freemarker.template.Configuration configuration = bean.createConfiguration();
        System.out.println(configuration.hashCode());
        return configuration;
    }

    @Bean
    @ConditionalOnNotWebApplication
    public freemarker.template.Configuration freemarkerConfiguration2() throws Exception {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:templates");
        bean.setDefaultEncoding("UTF-8");
        freemarker.template.Configuration configuration = bean.createConfiguration();
        log.debug("Configuration的hashCode()={}", configuration.hashCode());
        return configuration;
    }

}
