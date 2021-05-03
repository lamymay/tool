package com.arc.code.generator.test;

import com.alibaba.fastjson.JSON;
import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.config.template.ArcTemplateConfiguration;
import com.arc.code.generator.service.FreemarkerGeneratorService;
import com.arc.code.generator.service.impl.FreemarkerGeneratorServiceImpl;
import com.arc.code.generator.service.impl.MetaServiceImpl;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext.getArcPropertiesProvider;


/**
 * 启动类--非web Server方式『方案一』
 * Attention:   Do not launch the web container.
 * 注意：          运行main方法来生成代码，而不启动web容器
 * 作用：          仅仅创建用表来创建 model、mapper、service、controller 。
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/10/3 8:01
 */
public class LaunchGeneratorOverSpringContainerMain {

    private static final Logger log = LoggerFactory.getLogger(LaunchGeneratorOverSpringContainerMain.class);

    //@SuppressWarnings("resource")
    public static void main(String[] args) {

        crete1();
    }

    private static void crete1() {
        long t1 = System.currentTimeMillis();
        //0、Spring容器环境配置
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                MetaServiceImpl.class,
                ArcTemplateConfiguration.class,
                FreemarkerGeneratorServiceImpl.class
        );

        ArcTemplateConfiguration arcTemplateConfiguration = context.getBean(ArcTemplateConfiguration.class);
        Configuration configuration = context.getBean(Configuration.class);

        log.debug("arcTemplateConfiguration={}", arcTemplateConfiguration);
        log.debug("configuration={}", configuration);

        long t2 = System.currentTimeMillis();
        log.info("Spring容器环境配置耗时{}ms", (t2 - t1));

        //  1、获取配置参数
        ArcCodeGeneratorContext configContext = getArcPropertiesProvider();

        //  2、从Spring容器中获取生成工具bean   (干掉 mybatis 转而使用jdbc!)
        FreemarkerGeneratorService generatorService = context.getBean(FreemarkerGeneratorService.class);

        // 3、输出文件到文件夹
        ArcCodeGeneratorContext produceResult = generatorService.processByContext(configContext);


        long t3 = System.currentTimeMillis();
        log.info("生成过程耗时{}ms", (t3 - t2));

        log.warn("代码生成结果：{}", JSON.toJSONString(produceResult));
        // 记录结果
        String output = configContext.getOutput();

        boolean success = configContext.isSuccess();
        log.info("输出目录={}    \n生成结果={}", output, (success ? "成功" : "失败"));

        // 文件输出zip
        //        ZipFileUtil.outputFilesZip(output);

        //3、结果输出
        try {
            File file = new File(produceResult.getOutput());
            Desktop.getDesktop().open(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            log.error("error", exception);
        }
    }




}


//
//@Configuration
//@ComponentScan("com")
//@PropertySource("classpath:application.properties")
//@MapperScan("com.mybatis.generator.mapper")
//class SpringConfig {
//
//    public @Value("${spring.datasource.driver-class-name}")
//    String driverClassName;
//    public @Value("${spring.datasource.url}")
//    String url;
//    public @Value("${spring.datasource.username}")
//    String username;
//    public @Value("${spring.datasource.password}")
//    String password;

//    @Bean
//    public DataSource dataSource() {
//          DataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//        factory.setDataSource(dataSource);
//        return factory.getObject();
//    }
//
//    @Bean
//    public freemarker.template.Configuration freemarkerConfiguration() throws Exception {
//        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
//        bean.setTemplateLoaderPath("classpath:templates");
//        bean.setDefaultEncoding("UTF-8");
//        return bean.createConfiguration();
//    }
//}
