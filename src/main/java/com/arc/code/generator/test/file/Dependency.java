package com.arc.code.generator.test.file;

/**
 * 依赖模型
 *
 * @author may
 * @since 2021/5/3 22:06
 */
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

    public static String getMysqlDatabaseVersion80() {
        return MYSQL_DATABASE_VERSION80;
    }

    public static void setMysqlDatabaseVersion80(String mysqlDatabaseVersion80) {
        MYSQL_DATABASE_VERSION80 = mysqlDatabaseVersion80;
    }

    public static String getMysqlDatabaseVersion5x() {
        return MYSQL_DATABASE_VERSION5X;
    }

    public static void setMysqlDatabaseVersion5x(String mysqlDatabaseVersion5x) {
        MYSQL_DATABASE_VERSION5X = mysqlDatabaseVersion5x;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatabaseVersion() {
        return databaseVersion;
    }

    public void setDatabaseVersion(String databaseVersion) {
        this.databaseVersion = databaseVersion;
    }
}
