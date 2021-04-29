//package com.arc.code.generator.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import javax.sql.DataSource;
//
//
//@Slf4j
//@Configuration
//public class DataSourceConfigTest {
//
//    @Bean
//    @Profile("dev")
//    public DataSource embeddedDataSource() {
//
//        // 配置开发环境     嵌入式数据源
//    }
//
//
//    @Bean
//    @Profile("prod")
//    public DataSource jndiDataSource() {
//
//        // 配置生产环境    JNDI数据源
//
//    }
//}
