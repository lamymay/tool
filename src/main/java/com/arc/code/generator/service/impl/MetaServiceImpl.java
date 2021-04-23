package com.arc.code.generator.service.impl;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.mapper.MetaMapper;
import com.arc.code.generator.model.domain.meta.ColumnMeta;
import com.arc.code.generator.model.domain.meta.TableMeta;
import com.arc.code.generator.service.MetaService;
import com.arc.code.generator.utils.NameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.arc.code.generator.model.MockControl.scanAllTable;

/**
 * @author may
 * @since 2021/4/22 20:11
 */
@Component
public class MetaServiceImpl implements MetaService {

    private static final Logger log = LoggerFactory.getLogger(MetaServiceImpl.class);


    /**
     * 元数据查询提供
     */
    @Autowired
    private MetaMapper metaMapper;

    /**
     * 注入JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public float mysqlVersion = 8.0f;

    /**
     * @param tableSchema 数据库名称
     * @param tableName   表名称
     * @return
     */
    public static String getSqlSelectForTableMate(String tableSchema, String tableName) {
        return "select " +
                "t.table_schema, " +
                "t.table_name, " +
                "t.table_comment, " +
                "c.table_schema, " +
                "c.table_name, " +
                "c.column_name, " +
                "c.column_key, " +
                "c.data_type, " +
                "c.column_comment, " +
                "c.ordinal_position, " +
                "c.column_default, " +
                "c.is_nullable " +
                "from " +
                "information_schema.`tables`  t " +
                "inner join information_schema.columns c on t.table_schema = c.table_schema and t.table_name = c.table_name " +
                "where " +
                "t.table_schema = " + tableSchema + " and t.table_name = " + tableName;
    }


    // todo 通过系统配置在yml或properties文件中的数据源来获取
    // todo 缓存数据 cacheTableMeta(meta);

    /**
     * 获取表的元数据
     *
     * @return 表的元数据
     */
    @Override
    public TableMeta selectTableMateByMybatis(ArcPropertiesProvider arcPropertiesProvider) {
        TableMeta meta = metaMapper.get(arcPropertiesProvider.getDatabaseProperties().getSchemaName(), arcPropertiesProvider.getDatabaseProperties().getTableName());
        if (meta == null) {
            throw new IllegalArgumentException("\n失败！发生错误！指定的表不存在，请检查表的名称或数据库是否配置正确！\nFailure! Please check schemaName and tableName are correct. ");
        }
        return meta;
    }

    @Override
    public TableMeta selectTableMateOptimization(ArcPropertiesProvider arcPropertiesProvider, boolean useProjectDefaultDataSource) {
        TableMeta tableMeta = null;
        if (useProjectDefaultDataSource) {
            tableMeta = selectTableMateByMybatis(arcPropertiesProvider);
        } else {
            tableMeta = selectTableMateByJDBC(arcPropertiesProvider);
        }

        return tableMeta;
    }

    @Override
    public TableMeta selectTableMateByJDBC(ArcPropertiesProvider propertiesProvider) {
        Connection connection = null;
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        TableMeta tableMeta = null;

        try {
            String jdbcDriver = propertiesProvider.getDataSourceDriverClassName();
            String username = propertiesProvider.getDataSourceUsername();
            String password = propertiesProvider.getDataSourcePassword();
            String dbUrl = propertiesProvider.getDataSourceUrl();

            String schemaName = propertiesProvider.getSchemaName();
            String tableName = propertiesProvider.getTableName();


            // 注册 JDBC 驱动
            Class.forName(jdbcDriver);

            // 打开链接
            log.info("连接数据库....");
            connection = DriverManager.getConnection(dbUrl, username, password);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            preparedStatement = connection.prepareStatement(getSqlSelectForTableMate(schemaName, tableName));
            preparedStatement.setString(1, schemaName);
            preparedStatement.setString(2, tableName);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 展开结果集数据库
            tableMeta = new TableMeta();
            List<ColumnMeta> columns = new LinkedList<>();

            String table_name = null;
            String table_schema = null;
            String table_comment = null;
            while (resultSet.next()) {
                ColumnMeta columnMeta = new ColumnMeta();

                table_name = resultSet.getString("TABLE_NAME");
                table_schema = resultSet.getString("TABLE_SCHEMA");
                String col_column_name = resultSet.getString("COL_COLUMN_NAME");


                String col_column_key = resultSet.getString("COL_COLUMN_KEY");
                String col_data_type = resultSet.getString("COL_DATA_TYPE");
                table_comment = resultSet.getString("TABLE_COMMENT");

                int col_ordinal_position = resultSet.getInt("COL_ORDINAL_POSITION");
                String col_column_default = resultSet.getString("COL_COLUMN_DEFAULT");
                String col_is_nullable = resultSet.getString("COL_IS_NULLABLE");

                String col_table_schema = resultSet.getString("COL_TABLE_SCHEMA");
                String col_table_name = resultSet.getString("COL_TABLE_NAME");
                String col_column_comment = resultSet.getString("COL_COLUMN_COMMENT");

                columnMeta.setTableName(table_name);
                columnMeta.setTableSchema(table_schema);
                columnMeta.setColumnName(col_column_name);

                columnMeta.setColumnKey(col_column_key);
                columnMeta.setDataType(col_data_type);
                columnMeta.setColumnComment(col_column_comment);

                columnMeta.setOrdinalPosition(col_ordinal_position);
                columnMeta.setColumnDefault(col_column_default);
                columnMeta.setIsNullable(col_is_nullable);

                // 输出数据
                columns.add(columnMeta);
            }
            tableMeta.setColumns(columns);


            // 转换为驼峰
            if (table_name != null) {
                String[] arr = table_name.split("_");
                StringBuilder sb = new StringBuilder();
                for (String s : arr) {
                    sb.append(NameUtil.upperCaseFirstWord(s));
                }
                tableMeta.setTableName(sb.toString());
            }

            tableMeta.setTableComment(table_comment);
            tableMeta.setTableSchema(table_schema);

            // 完成后关闭
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
            log.error("结果={}", se);
        } catch (Exception e2) {
            // 处理 Class.forName 错误
            e2.printStackTrace();
            log.error("结果={}", e2);
        } finally {
            // 关闭资源
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        log.info("jdbc 结束={}");
        return tableMeta;
    }


    @Override
    public List<TableMeta> selectTableMateListOptimization(ArcPropertiesProvider arcPropertiesProvider, boolean useProjectDefaultDataSource) {
        List<TableMeta> tableMateList = new ArrayList<>();

        if(scanAllTable){
        //if(arcPropertiesProvider.isScanAllTable()){
            // todo 查整个库的

        }

        tableMateList.add(selectTableMateOptimization(arcPropertiesProvider, useProjectDefaultDataSource));
        return tableMateList;

    }

    /**
     * jdbc 连接mysql获取数据
     *
     * @return
     */
    @Deprecated
    private TableMeta selectTableMateByCustomizeJdbcBackup(ArcCodeGeneratorContext propertiesProvider) {
        Connection connection = null;
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        TableMeta tableMeta = null;

        try {
            String jdbcDriver = propertiesProvider.getDriverClassName();
            String username = propertiesProvider.getUsername();
            String password = propertiesProvider.getPassword();
            String dbUrl = propertiesProvider.getUrl();

            String schemaName = propertiesProvider.getSchemaName();
            String tableName = propertiesProvider.getTableName();


            // 注册 JDBC 驱动
            Class.forName(jdbcDriver);

            // 打开链接
            log.info("连接数据库....");
            connection = DriverManager.getConnection(dbUrl, username, password);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            preparedStatement = connection.prepareStatement(getSqlSelectForTableMate("test", "test"));
            preparedStatement.setString(1, schemaName);
            preparedStatement.setString(2, tableName);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 展开结果集数据库
            tableMeta = new TableMeta();
            List<ColumnMeta> columns = new LinkedList<>();

            String table_name = null;
            String table_schema = null;
            String table_comment = null;
            while (resultSet.next()) {
                ColumnMeta columnMeta = new ColumnMeta();

                table_name = resultSet.getString("TABLE_NAME");
                table_schema = resultSet.getString("TABLE_SCHEMA");
                String col_column_name = resultSet.getString("COL_COLUMN_NAME");


                String col_column_key = resultSet.getString("COL_COLUMN_KEY");
                String col_data_type = resultSet.getString("COL_DATA_TYPE");
                table_comment = resultSet.getString("TABLE_COMMENT");

                int col_ordinal_position = resultSet.getInt("COL_ORDINAL_POSITION");
                String col_column_default = resultSet.getString("COL_COLUMN_DEFAULT");
                String col_is_nullable = resultSet.getString("COL_IS_NULLABLE");

                String col_table_schema = resultSet.getString("COL_TABLE_SCHEMA");
                String col_table_name = resultSet.getString("COL_TABLE_NAME");
                String col_column_comment = resultSet.getString("COL_COLUMN_COMMENT");

                columnMeta.setTableName(table_name);
                columnMeta.setTableSchema(table_schema);
                columnMeta.setColumnName(col_column_name);

                columnMeta.setColumnKey(col_column_key);
                columnMeta.setDataType(col_data_type);
                columnMeta.setColumnComment(col_column_comment);

                columnMeta.setOrdinalPosition(col_ordinal_position);
                columnMeta.setColumnDefault(col_column_default);
                columnMeta.setIsNullable(col_is_nullable);

                // 输出数据
                columns.add(columnMeta);
            }
            tableMeta.setColumns(columns);


            // 转换为驼峰
            if (table_name != null) {
                String[] arr = table_name.split("_");
                StringBuilder sb = new StringBuilder();
                for (String s : arr) {
                    sb.append(NameUtil.upperCaseFirstWord(s));
                }
                tableMeta.setTableName(sb.toString());
            }

            tableMeta.setTableComment(table_comment);
            tableMeta.setTableSchema(table_schema);

            // 完成后关闭
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
            log.error("结果={}", se);
        } catch (Exception e2) {
            // 处理 Class.forName 错误
            e2.printStackTrace();
            log.error("结果={}", e2);
        } finally {
            // 关闭资源
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        log.info("jdbc 结束={}");
        return tableMeta;
    }

    //            String jdbcDriver = (String) parameterMap.get("driverClassName");
//            String username = (String) parameterMap.get("username");
//            String password = (String) parameterMap.get("password");
//            String dbUrl = (String) parameterMap.get("url");
//            String schemaName = (String) parameterMap.get("schemaName");
//            String tableName = (String) parameterMap.get("tableName");

//    /**
//     * 缓存数据
//     *
//     * @param meta
//     */
//    @Deprecated
//    private void cacheTableMeta(TableMeta meta) {
//        log.info("缓存数据{}", meta);
//        FileOutputStream fos = null;
//        try {
//            File file = new File("T:\\Project\\Za\\tool\\src\\main\\resources\\templates\\serializable\\SerializableTableMeta.txt");
//            //创建父级目录
//            System.out.println(file.getParent());
//            String parent = file.getParent();
//            File parentFile = new File(parent);
//            System.out.println(parentFile.exists());
//
//
//            if (!parentFile.exists()) {
//                boolean mkdirs = parentFile.mkdirs();
//                if (mkdirs == false) {
//                    throw new RuntimeException("临时文件父级路径创建失败");
//                }
//            }
//
////            if (!parentFile.isDirectory()) {
////                boolean mkdirs = parentFile.mkdirs();
////                if (mkdirs == false) {
////                    throw new RuntimeException("临时文件父级路径创建失败");
////                }
////            }
//            System.out.println("parentFile 是目录？" + parentFile.isDirectory());
//            System.out.println("file.getPath()" + file.getPath());
//            System.out.println("file.getName() " + file.getName());
//            System.out.println("文件是个目录？ " + file.isDirectory());
//
//            if (!file.exists()) {
////                boolean mkdirs = file.mkdirs();
////                if (mkdirs == false) {
////                    throw new RuntimeException("临时文件父级路径创建失败");
////                }
//                boolean newFile = file.createNewFile();
//                if (newFile == false) {
//                    throw new RuntimeException("临时文件创建失败");
//                }
//            }
//            fos = new FileOutputStream(file);
//            ObjectOutputStream objectOutputStream = null;
//            objectOutputStream = new ObjectOutputStream(fos);
//            objectOutputStream.writeObject(meta);
//            objectOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//        if (meta == null) {
//            throw new IllegalArgumentException("\n指定的表不存在，请检查表的名称或数据库是否配置正确！\nPlease check schemaName and tableName are correct. ");
//        }
//        log.info("表的元信息为{}", JacksonUtils.toJson(meta));
//    }

}
