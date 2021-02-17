package com.arc.test.freemarker;

import com.arc.code.generator.config.properties.auto.DataSourceProperties;
import com.arc.code.generator.config.properties.auto.DatabaseProperties;
import com.arc.code.generator.config.properties.auto.ProjectProperties;
import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.config.properties.impl.ArcPropertiesProviderImpl1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author May
 * @since 2020/1/6 11:34
 */
@Slf4j
public class EnvironmentTest {

    public static void main(String[] args) {
        boolean loadResult = loadContext();
        log.debug("结果={}",loadResult);
    }

    private static boolean loadContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                ArcPropertiesProviderImpl1.class
        );
        ArcPropertiesProvider arcPropertiesProvider = context.getBean(ArcPropertiesProvider.class);
        log.warn("结果={}", arcPropertiesProvider);
        log.debug("结果={}", arcPropertiesProvider);
        log.info("结果={}", arcPropertiesProvider);

        //String
        System.out.println("数据库连接地址:" + arcPropertiesProvider.getDataSourceUrl());
        System.out.println("mapper命名空间:" + arcPropertiesProvider.getProjectMapperNamespace());
        System.out.println("数据库名称:" + arcPropertiesProvider.getSchemaName());
        System.out.println("表名称:" + arcPropertiesProvider.getTableName());
        System.out.println("表别名:" + arcPropertiesProvider.getTableAlias());

        log.info("数据库连接地址:{}" , arcPropertiesProvider.getDataSourceUrl());
        log.info("数据库连接账号:{}" , arcPropertiesProvider.getDataSourceUsername());
        log.info("数据库连接密码:{}" , arcPropertiesProvider.getDataSourcePassword());
        log.info("数据库连接驱动:{}", arcPropertiesProvider.getDataSourceDriverClassName());
        log.info("数据库名称:{}" , arcPropertiesProvider.getSchemaName());
        log.info("数据库表名称:{}" , arcPropertiesProvider.getTableName());
        log.info("数据库表别名:{}" , arcPropertiesProvider.getTableAlias());

        //对象
        DataSourceProperties dataSourceProperties = arcPropertiesProvider.getDataSourceProperties();
        DatabaseProperties databaseProperties = arcPropertiesProvider.getDatabaseProperties();
        ProjectProperties projectProperties = arcPropertiesProvider.getProjectProperties();

        System.out.println(dataSourceProperties);
        System.out.println(databaseProperties);
        System.out.println(projectProperties);
        return true;
    }

    private static boolean loadContext2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                //ArcCodeGeneratorPropertiesProvider.class,
                //DataSourceConfig.class,
                //MybatisConfiguration.class,
                //ArcTemplateConfiguration.class,
                ArcPropertiesProviderImpl1.class
                //FreemarkerGeneratorServiceImpl.class
        );

        // DataSource dataSource = context.getBean(DataSource.class);
        //log.debug("结果={}", dataSource);
        //System.out.println("dataSource=" + JSON.toJSONString(dataSource));
        ArcPropertiesProvider arcPropertiesProvider = context.getBean(ArcPropertiesProvider.class);
        log.debug("结果={}", arcPropertiesProvider);

        //FreemarkerGeneratorServiceImpl mapperGenerator = context.getBean(FreemarkerGeneratorServiceImpl.class);
        //Map<String, Object> produce = mapperGenerator.produce();
        //Map<String, Object> produce = context.getBean(FreemarkerGeneratorServiceImpl.class).produce();
        //log.info("代码生成目录在{}，生成结果 {}", produce.get("out"), produce.get("result"));


        return true;
    }


}
