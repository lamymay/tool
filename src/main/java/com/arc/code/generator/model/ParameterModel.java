package com.arc.code.generator.model;

/**
 * @author may
 * @since 2021/4/16 18:19
 */
public class ParameterModel {

    private String url = ""; //数据库连接url
    private String username = "root";  //数据库账号
    private String password = "admin"; //数据库密码
    private String driverClassName = "com.mysql.cj.jdbc.Driver"; //驱动名称
    public String schemaName = "";//schemaName(数据库名)【必要】
    public String tableName = "";//数据库表名称
    public String tableAlias = "";//数据库表在mapper的sql中的别名


}
