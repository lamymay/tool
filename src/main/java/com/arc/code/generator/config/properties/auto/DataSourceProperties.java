package com.arc.code.generator.config.properties.auto;

import lombok.*;

/**
 * jdbc 连接相关配置
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since: 2019/5/8 22:24
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class DataSourceProperties {

    //arc.generator.dataSource.url=
    // arc.generator.dataSource.username=root
    //jdbc:mysql://127.0.0.1:3306/test3?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8
    private String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8"; //数据库连接url
    private String username = "root";  //数据库账号
    private String password = "admin"; //数据库密码
    private String driverClassName = "com.mysql.cj.jdbc.Driver"; //驱动名称


    public DataSourceProperties(final String url, final String username, final String password, final String driverClassName) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverClassName = driverClassName;
    }
}
