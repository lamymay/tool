package com.arc.code.generator.config.template;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * freemarker 配置类
 *
 * @author lamyamay
 * @since 2019/10/25 22:17
 */
@Configuration
public class ArcTemplateConfiguration {

    //结合使用注解@ConditionalOnMissingClass和@Bean,可以仅当某些类不存在于 classpath 上时候才创建某个Bean：
    //@ConditionalOnMissingClass(value={"com.sample.Dummy","com.sample.Dum"})
    @Bean
    @ConditionalOnMissingClass(value = {"org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer"})
    public freemarker.template.Configuration freemarkerConfiguration() throws Exception {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:templates");
        bean.setDefaultEncoding("UTF-8");
        return bean.createConfiguration();
    }
}
