package com.arc.code.generator.config.properties.value;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 测试属性注入
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/10/3 15:37
 */
@Setter
@Getter
@Component
public class TestValue {

    //    @Value("${meta.columnPrefix}")    private String columnPrefix;

    //    @Value("${meta.javaPackage}")    private String javaPackage;

    //    @Value("${meta.mapperNamespace}")    private String mapperNamespace;

    //    @Value("${spring.datasource.driver-class-name}")
    //    private String driverClassName;

    //    @Value("${spring.datasource.url}")
    //    private String url;

    //    @Value("${spring.datasource.username}")
    //    private String username;

    //    @Value("${spring.datasource.password}")
    //    private String password;

    @Value("${arc.generator.dataSource.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;

    @Value("${arc.generator.dataSource.url:jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8}")
    private String url;

    @Value("${arc.generator.dataSource.username:''}")
    private String username;

    @Value("${arc.generator.dataSource.password}:''")
    private String password;
}
