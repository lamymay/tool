package com.arc.code.generator.test;

import com.alibaba.fastjson.JSON;
import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.config.properties.auto.ArcCodeGeneratorPropertiesProvider;
import com.arc.code.generator.config.properties.auto.EnableArcCorePropertiesConfig;
import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.service.FreemarkerGeneratorService;
import com.arc.code.generator.service.impl.FreemarkerGeneratorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

import static com.arc.code.generator.controller.data.GenerateCodeV1Controller.openOutputDir;


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
    public static void main(String[] args) throws IOException {
        long t1 = System.currentTimeMillis();
        //0、Spring容器环境配置
        //todo  仅仅使用数据自动装配【EnableArcCorePropertiesConfig】启动spring容器或者自动配置的方式是null


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                EnableArcCorePropertiesConfig.class,
                //ArcPropertiesProviderImpl1.class,
                ArcCodeGeneratorPropertiesProvider.class,

//                DataSourceConfig.class,
//                MybatisConfiguration.class,
//                ArcTemplateConfiguration.class
                FreemarkerGeneratorServiceImpl.class

        );

//        context.register(MetaMapper.class);

//        String resource = "MetaMapper.xml";
//        Reader reader = Resources.getResourceAsReader(resource);
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory = builder.build(reader);
//        SqlSession session = factory.openSession();
//        MetaMapper user = session.selectOne(
//                "com.liangjidong.mapper.UserMapper.selectById", 1);
//        System.out.println(user);


        long t2 = System.currentTimeMillis();
        log.info("Spring容器环境配置耗时{}ms", (t2 - t1));

        //  1、从Spring容器中获取生成工具bean    2、调用执行方法，产生需要的文件
        // todo 干掉 mybatis 转而使用jdbc!
        FreemarkerGeneratorService generatorService = context.getBean(FreemarkerGeneratorService.class);

        ArcPropertiesProvider propertiesProvider = getArcPropertiesProvider();
        ArcPropertiesProvider produceResult = generatorService.processByContext(propertiesProvider);


        long t3 = System.currentTimeMillis();
        log.info("生成过程耗时{}ms", (t3 - t2));

        log.warn("代码生成结果：{}", JSON.toJSONString(produceResult));
        //3、结果输出
        openOutputDir(produceResult.getOutput());
    }

    private static ArcPropertiesProvider getArcPropertiesProvider() {
        ArcCodeGeneratorContext generatorContext = new ArcCodeGeneratorContext();
        generatorContext.setUrl("jdbc:mysql://122.51.110.127:3306/test?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8");
        generatorContext.setUsername("root");
        generatorContext.setPassword("admin");
        generatorContext.setDriverClassName("com.mysql.cj.jdbc.Driver");

        //数据库名 改进一下不要指定了
        generatorContext.setSchemaName("admin");
        generatorContext.setTableName("admin");
        generatorContext.setTableAlias("admin");
        generatorContext.setOnlyModelMapperAndXml(false);

        generatorContext.setAuthor("叶超");
        generatorContext.setRootNamespace("com.demo");
        generatorContext.setMapperNamespace("com.demo.mapper");
        generatorContext.setServiceNamespace("com.demo.service");
        generatorContext.setServiceImplNamespace("com.demo.service.impl");
        generatorContext.setControllerNamespace("com.demo.service.impl");

        generatorContext.setCommentFormatAsEndOfLine(true);
        generatorContext.setOutput("D:\\free");

        return generatorContext;

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
