package com.arc.code.generator;

import com.arc.code.generator.config.db.DataSourceConfig;
import com.arc.code.generator.config.db.MybatisConfiguration;
import com.arc.code.generator.config.properties.auto.ArcCodeGeneratorPropertiesProvider;
import com.arc.code.generator.config.properties.auto.EnableArcCorePropertiesConfig;
import com.arc.code.generator.config.properties.impl.ArcPropertiesProviderImpl1;
import com.arc.code.generator.config.template.ArcTemplateConfiguration;
import com.arc.code.generator.service.impl.FreemarkerGeneratorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;


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

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        //0、Spring容器环境配置
        //todo  仅仅使用数据自动装配【EnableArcCorePropertiesConfig】启动spring容器或者自动配置的方式是null
        ApplicationContext context = new AnnotationConfigApplicationContext(
                EnableArcCorePropertiesConfig.class,
                ArcPropertiesProviderImpl1.class,
                ArcCodeGeneratorPropertiesProvider.class,
                DataSourceConfig.class,
                MybatisConfiguration.class,
                ArcTemplateConfiguration.class,
                FreemarkerGeneratorServiceImpl.class

        );
        long t2 = System.currentTimeMillis();
        //  1、从Spring容器中获取生成工具bean    2、调用执行方法，产生需要的文件
        Map<String, Object> produce = context.getBean(FreemarkerGeneratorServiceImpl.class).execute(null);

        //3、结果输出
        long t3 = System.currentTimeMillis();
        log.info("Spring容器环境配置耗时{}ms，生成过程耗时{}ms", (t2 - t1), (t3 - t2));
        Boolean result = (Boolean) produce.get("result");
        log.info("\n代码生成输出目录为 {}    \n生成结果：{}", produce.get("output"), (result ? "成功" : "失败"));
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
