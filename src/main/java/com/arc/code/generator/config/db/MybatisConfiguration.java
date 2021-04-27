//package com.arc.code.generator.config.db;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * Mybatis配置类
// *
// * @author lamyamay
// * @since 2019/10/25 22:17
// */
//@Slf4j
//@Configuration
//@MapperScan("com.arc.code.generator.mapper")
////@PropertySource("classpath:application.properties")
//public class MybatisConfiguration {
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//        factory.setDataSource(dataSource);
//        SqlSessionFactory sqlSessionFactory = factory.getObject();
//
//        log.info("时刻:{},数据源 DataSource :{}" , System.currentTimeMillis(),dataSource);
//        log.info("时刻:{},SqlSessionFactory :{}" ,System.currentTimeMillis(), sqlSessionFactory);
//        return sqlSessionFactory ;
//    }
//}
