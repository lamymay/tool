//package com.arc.code.generator.service.impl;
//
//import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
//import com.arc.code.generator.model.domain.meta.ColumnMeta;
//import com.arc.code.generator.model.domain.meta.TableMeta;
//import com.arc.code.generator.utils.NameUtil;
//
//import java.sql.*;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * @author may
// * @since 2021/4/27 15:02
// */
//public class JDBCTest {
//
//    /**
//     * jdbc 连接mysql获取数据
//     *
//     * @return
//     */
//    @Deprecated
//    private TableMeta selectTableMateByCustomizeJdbcBackup(ArcCodeGeneratorContext propertiesProvider) {
//        Connection connection = null;
//        //Statement statement = null;
//        PreparedStatement preparedStatement = null;
//        TableMeta tableMeta = null;
//
//        try {
//            String jdbcDriver = propertiesProvider.getDriverClassName();
//            String username = propertiesProvider.getUsername();
//            String password = propertiesProvider.getPassword();
//            String dbUrl = propertiesProvider.getUrl();
//
//            String schemaName = propertiesProvider.getSchemaName();
//            String tableName = propertiesProvider.getTableName();
//
//
//            // 注册 JDBC 驱动
//            Class.forName(jdbcDriver);
//
//            // 打开链接
//            log.info("连接数据库....");
//            connection = DriverManager.getConnection(dbUrl, username, password);
//
//            // 执行查询
//            System.out.println(" 实例化Statement对象...");
//            preparedStatement = connection.prepareStatement(getSqlSelectForTableMate("test", "test"));
//            preparedStatement.setString(1, schemaName);
//            preparedStatement.setString(2, tableName);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            // 展开结果集数据库
//            tableMeta = new TableMeta();
//            List<ColumnMeta> columns = new LinkedList<>();
//
//            String table_name = null;
//            String table_schema = null;
//            String table_comment = null;
//            while (resultSet.next()) {
//                ColumnMeta columnMeta = new ColumnMeta();
//
//                table_name = resultSet.getString("TABLE_NAME");
//                table_schema = resultSet.getString("TABLE_SCHEMA");
//                String col_column_name = resultSet.getString("COL_COLUMN_NAME");
//
//
//                String col_column_key = resultSet.getString("COL_COLUMN_KEY");
//                String col_data_type = resultSet.getString("COL_DATA_TYPE");
//                table_comment = resultSet.getString("TABLE_COMMENT");
//
//                int col_ordinal_position = resultSet.getInt("COL_ORDINAL_POSITION");
//                String col_column_default = resultSet.getString("COL_COLUMN_DEFAULT");
//                String col_is_nullable = resultSet.getString("COL_IS_NULLABLE");
//
//                String col_table_schema = resultSet.getString("COL_TABLE_SCHEMA");
//                String col_table_name = resultSet.getString("COL_TABLE_NAME");
//                String col_column_comment = resultSet.getString("COL_COLUMN_COMMENT");
//
//                columnMeta.setTableName(table_name);
//                columnMeta.setTableSchema(table_schema);
//                columnMeta.setColumnName(col_column_name);
//
//                columnMeta.setColumnKey(col_column_key);
//                columnMeta.setDataType(col_data_type);
//                columnMeta.setColumnComment(col_column_comment);
//
//                columnMeta.setOrdinalPosition(col_ordinal_position);
//                columnMeta.setColumnDefault(col_column_default);
//                columnMeta.setIsNullable(col_is_nullable);
//
//                // 输出数据
//                columns.add(columnMeta);
//            }
//            tableMeta.setColumns(columns);
//
//
//            // 转换为驼峰
//            if (table_name != null) {
//                String[] arr = table_name.split("_");
//                StringBuilder sb = new StringBuilder();
//                for (String s : arr) {
//                    sb.append(NameUtil.upperCaseFirstWord(s));
//                }
//                tableMeta.setTableName(sb.toString());
//            }
//
//            tableMeta.setTableComment(table_comment);
//            tableMeta.setTableSchema(table_schema);
//
//            // 完成后关闭
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException se) {
//            // 处理 JDBC 错误
//            se.printStackTrace();
//            log.error("结果={}", se);
//        } catch (Exception e2) {
//            // 处理 Class.forName 错误
//            e2.printStackTrace();
//            log.error("结果={}", e2);
//        } finally {
//            // 关闭资源
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException se2) {
//            }// 什么都不做
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        log.info("jdbc 结束={}");
//        return tableMeta;
//    }
//}
