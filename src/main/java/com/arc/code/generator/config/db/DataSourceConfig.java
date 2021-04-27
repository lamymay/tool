//package com.arc.code.generator.config.db;
//
//import com.arc.code.generator.config.properties.ArcPropertiesProvider;
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//
///**
// * 数据源配置
// *
// * @author May
// */
//@Slf4j
//@Configuration
//public class DataSourceConfig {
//
//    //@Autowired private TestValue testValue;
//
//    @Autowired
//    @Qualifier("arcPropertiesProviderImpl1")
//    private ArcPropertiesProvider arcPropertiesProvider;
//
//
//    //参考          //https://www.cnblogs.com/hongdada/p/9360155.html
//    @Bean
//    public DataSource dataSource() {
//
//        String url = null;
//        String username = null;
//        String password = null;
//        String driverClassName = null;
//
//        //========================= 方式 1 硬编码 ========================
//        //        url = "jdbc:mysql://localhost:3306/test3?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8";
//        //        username = "root";
//        //        password = "admin";
//        //        driverClassName = "com.mysql.cj.jdbc.Driver";
//
//        //========================= 方式 2 接口注入========================
//        log.debug("顶级接口定义参数arcPropertiesProvider={}", arcPropertiesProvider);
//        log.info("顶级接口定义参数arcPropertiesProvider={}", arcPropertiesProvider);
//        url = arcPropertiesProvider.getDataSourceUrl();
//        username = arcPropertiesProvider.getDataSourceUsername();
//        password = arcPropertiesProvider.getDataSourcePassword();
//        driverClassName = arcPropertiesProvider.getDataSourceDriverClassName();
//
//        //========================= 方式 3 bean注入 ========================
//
//        //        log.info("generatorProperties={}", generatorProperties);
//        //        String driverClassName = generatorProperties.getDataSource().getDriverClassName();
//        //        String url = generatorProperties.getDataSource().getUrl();
//        //        String username = generatorProperties.getDataSource().getUsername();
//        //        String password = generatorProperties.getDataSource().getPassword();
//
//
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(url);
//        hikariConfig.setUsername(username);
//        hikariConfig.setPassword(password);
//        hikariConfig.setDriverClassName(driverClassName);
//        hikariConfig.addDataSourceProperty("cachePrepStmts", "true"); //是否自定义配置，为true时下面两个参数才生效
//        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250"); //连接池大小默认25，官方推荐250-500
//        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //单条语句最大长度默认256，官方推荐2048
//        hikariConfig.addDataSourceProperty("useServerPrepStmts", "true"); //新版本MySQL支持服务器端准备，开启能够得到显著性能提升
//        hikariConfig.addDataSourceProperty("useLocalSessionState", "true");
//        hikariConfig.addDataSourceProperty("useLocalTransactionState", "true");
//        hikariConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
//        hikariConfig.addDataSourceProperty("cacheResultSetMetadata", "true");
//        hikariConfig.addDataSourceProperty("cacheServerConfiguration", "true");
//        hikariConfig.addDataSourceProperty("elideSetAutoCommits", "true");
//        hikariConfig.addDataSourceProperty("maintainTimeStats", "false");
//        DataSource dataSource = new HikariDataSource(hikariConfig);
//        //========================= 输出数据源相关关键参数 ========================
//
//        log.info("输出数据源相关关键参数,时刻:{}------------> ", System.currentTimeMillis());
//        log.info("数据库连接地址:{}", arcPropertiesProvider.getDataSourceUrl());
//        log.info("数据库连接账号:{}", arcPropertiesProvider.getDataSourceUsername());
//        log.info("数据库连接密码:{}", arcPropertiesProvider.getDataSourcePassword());
//        log.info("数据库连接驱动:{}", arcPropertiesProvider.getDataSourceDriverClassName());
//        log.info("数据库名称:{}", arcPropertiesProvider.getSchemaName());
//        log.info("数据库表名称:{}", arcPropertiesProvider.getTableName());
//        log.info("数据库表别名:{}", arcPropertiesProvider.getTableAlias());
//        log.info("数据源 DataSource :{}", dataSource);
//        return dataSource;
//    }
//
//    //     第二种中创建 DataSource
//    //    //@Bean
//    //    public DataSource dataSourceBasicDataSource() {
//    //        String username = "root";
//    //        String password = "admin";
//    //        String driverClassName = "com.mysql.cj.jdbc.Driver";
//    //        String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8";
//    //
//    //        BasicDataSource dataSource = new BasicDataSource();
//    //        dataSource.setDriverClassName(driverClassName);
//    //        dataSource.setUrl(url);
//    //        dataSource.setUsername(username);
//    //        dataSource.setPassword(password);
//    //        return dataSource;
//    //    }
//
//}
