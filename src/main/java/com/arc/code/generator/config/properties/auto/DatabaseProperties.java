package com.arc.code.generator.config.properties.auto;

import lombok.*;

/**
 * 表的名称、在mapper中的别名 相关属性
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/5/22 21:47
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class DatabaseProperties {

    /**
     * schemaName(数据库名)
     * 【必要】
     */
    public String schemaName = "";

    /**
     * 数据库表名称
     */
    public String tableName = "";

    /**
     * 数据库表在mapper的sql中的别名
     */
    public String tableAlias = "";

    public DatabaseProperties(final String schemaName, final String tableName, final String tableAlias) {
        this.schemaName = schemaName;
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }
}
