package com.arc.code.generator.test.file;

import lombok.Data;

/**
 * 依赖模型
 *
 * @author may
 * @since 2021/5/3 22:06
 */
@Data
public class Dependency {

    public static String MYSQL_DATABASE_VERSION80 = "8.0";
    public static String MYSQL_DATABASE_VERSION5X = "5.X";
    /**
     * 数据库
     * MySQL8.0
     * MySQL5.X
     * H2
     */
    private String database;

    /**
     * 数据库版本
     */
    private String databaseVersion;
}
