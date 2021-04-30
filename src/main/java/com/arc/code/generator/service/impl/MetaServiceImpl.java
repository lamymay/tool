package com.arc.code.generator.service.impl;

import com.alibaba.fastjson.JSON;
import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.model.domain.ColumnMeta;
import com.arc.code.generator.model.domain.TableMeta;
import com.arc.code.generator.service.MetaService;
import com.arc.code.generator.utils.NameUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.*;
import java.util.*;

/**
 * @author may
 * @since 2021/4/22 20:11
 */
@Component
public class MetaServiceImpl implements MetaService {

    private static final Logger log = LoggerFactory.getLogger(MetaServiceImpl.class);

    public MetaServiceImpl() {
        System.out.println("MetaServiceImpl初始化!!");
    }


//    /**
//     * 元数据查询提供
//     */
//    @Autowired
//    private MetaMapper metaMapper;

//    /**
//     * 注入JdbcTemplate
//     */
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public float mysqlVersion = 8.0f;


    /*
      通过系统配置在yml或properties文件中的数据源来获取
      是否使用 mybatis来获取, 理由:1非必要并且需要添加依赖 2利用jdbc已经可以满足查询出来了
    */

    @Override
    public TableMeta selectTableMateByJDBC(ArcCodeGeneratorContext generatorContext) {
        Assert.notNull(generatorContext, "获取表元元素异常,连接数据库依赖的配置错或为空!");
        return selectTableMateByJDBC(generatorContext.getUrl(),
                generatorContext.getUser(),
                generatorContext.getPassword(),
                generatorContext.getDriverClassName(),
                generatorContext.getSchemaName(),
                generatorContext.getTableName());
    }

    @Override
    public TableMeta selectTableMateByJDBC(String url, String user, String password, String driverClassName,
                                           String schemaName, String tableName) {
        Connection connection = null;
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        TableMeta tableMeta = null;

        try {
            // 注册 JDBC 驱动
            Class.forName(driverClassName);
            // 打开链接
            log.info("连接数据库....");
            connection = DriverManager.getConnection(url, user, password);
            // 执行查询
          log.info("JDBC 连接数据OK ...");
            String sql = "select t.table_schema AS TABLE_SCHEMA  ,t.table_name AS TABLE_NAME,t.table_comment AS TABLE_COMMENT, "

                    + "c.table_schema AS COL_TABLE_SCHEMA,c.table_name AS COL_TABLE_NAME,c.column_name AS COL_COLUMN_NAME, "
                    + "c.column_key AS COL_COLUMN_KEY,c.data_type AS COL_DATA_TYPE,c.column_comment AS COL_COLUMN_COMMENT, "
                    + "c.ordinal_position AS COL_ORDINAL_POSITION,c.column_default AS COL_COLUMN_DEFAULT,c.is_nullable AS COL_IS_NULLABLE "


                    + "from information_schema.`tables` AS t "
                    + "inner join information_schema.columns AS c ON t.table_schema = c.table_schema and t.table_name = c.table_name "
                    + "where t.table_schema = ? and t.table_name = ? ";
            preparedStatement = connection.prepareStatement(sql);
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

                table_schema = resultSet.getString("TABLE_SCHEMA");
                table_name = resultSet.getString("TABLE_NAME");
                table_comment = resultSet.getString("TABLE_COMMENT");

                String col_table_schema = resultSet.getString("COL_TABLE_SCHEMA");
                String col_table_name = resultSet.getString("COL_TABLE_NAME");
                String col_column_name = resultSet.getString("COL_COLUMN_NAME");

                String col_column_key = resultSet.getString("COL_COLUMN_KEY");
                String col_data_type = resultSet.getString("COL_DATA_TYPE");
                String col_column_comment = resultSet.getString("COL_COLUMN_COMMENT");

                int col_ordinal_position = resultSet.getInt("COL_ORDINAL_POSITION");
                String col_column_default = resultSet.getString("COL_COLUMN_DEFAULT");
                String col_is_nullable = resultSet.getString("COL_IS_NULLABLE");


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
        } catch (SQLException exception) {
            // 处理 JDBC 错误
            log.error("ERROR", exception);
        } catch (Exception exception) {
            // 处理 Class.forName 错误
            log.error("ERROR", exception);
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


    @Override
    public List<TableMeta> selectListOptimization(ArcCodeGeneratorContext generatorContext) {
        List<TableMeta> tableMateList = new ArrayList<>();

        if (StringUtils.isBlank(generatorContext.getTableName())) {
            // 查整个库的
            List<TableMeta> tableMetaList = selectTableMateByJDBC(generatorContext.getUrl(),
                    generatorContext.getUser(),
                    generatorContext.getPassword(),
                    generatorContext.getDriverClassName(),
                    generatorContext.getSchemaName());

            tableMateList.addAll(tableMetaList);
            return tableMetaList;
        }

        TableMeta tableMeta = selectTableMateByJDBC(generatorContext.getUrl(),
                generatorContext.getUser(),
                generatorContext.getPassword(),
                generatorContext.getDriverClassName(),
                generatorContext.getSchemaName(),
                generatorContext.getTableName());

        tableMateList.add(tableMeta);
        return tableMateList;
    }

    /**
     * 查整个db中模型
     */
    static String select_all_sql = "select t.table_schema AS TABLE_SCHEMA  ,t.table_name AS TABLE_NAME,t.table_comment AS TABLE_COMMENT, "

            + "c.table_schema AS COL_TABLE_SCHEMA,c.table_name AS COL_TABLE_NAME,c.column_name AS COL_COLUMN_NAME, "
            + "c.column_key AS COL_COLUMN_KEY,c.data_type AS COL_DATA_TYPE,c.column_comment AS COL_COLUMN_COMMENT, "
            + "c.ordinal_position AS COL_ORDINAL_POSITION,c.column_default AS COL_COLUMN_DEFAULT,c.is_nullable AS COL_IS_NULLABLE "


            + "from information_schema.`tables` AS t "
            + "inner join information_schema.columns AS c ON t.table_schema = c.table_schema and t.table_name = c.table_name "
            + "where t.table_schema = ?  ";

    /**
     * 查整个db中模型
     *
     * @param url
     * @param user
     * @param password
     * @param driverClassName
     * @param schemaName
     * @return
     */
    private List<TableMeta> selectTableMateByJDBC(String url, String user, String password, String driverClassName, String schemaName) {

        // key 是表名称 value是模型
        Map<String, TableMeta> tempMap = new HashMap<>(16);
        Connection connection = null;
        //Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {

            // 注册 JDBC 驱动
            Class.forName(driverClassName);

            // 打开链接
            log.info("连接数据库....");
            connection = DriverManager.getConnection(url, user, password);
            log.info("连接数据库....");

            // 执行查询
            preparedStatement = connection.prepareStatement(select_all_sql);
            log.debug("查整个db中模型,sql:\n{},参数:{}", select_all_sql, schemaName);
            preparedStatement.setString(1, schemaName);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 展开结果集数据库
            List<ColumnMeta> columns = new LinkedList<>();
            while (resultSet.next()) {
                String table_schema = resultSet.getString("TABLE_SCHEMA");// 库
                String table_name = resultSet.getString("TABLE_NAME");//表名称
                String col_table_name = resultSet.getString("COL_TABLE_NAME");//表名称

                String col_table_schema = resultSet.getString("COL_TABLE_SCHEMA");
                String col_column_name = resultSet.getString("COL_COLUMN_NAME");

                String col_column_key = resultSet.getString("COL_COLUMN_KEY");
                String col_data_type = resultSet.getString("COL_DATA_TYPE");
                String col_column_comment = resultSet.getString("COL_COLUMN_COMMENT");

                int col_ordinal_position = resultSet.getInt("COL_ORDINAL_POSITION");
                String col_column_default = resultSet.getString("COL_COLUMN_DEFAULT");
                String col_is_nullable = resultSet.getString("COL_IS_NULLABLE");

                ColumnMeta columnMeta = new ColumnMeta();
                columnMeta.setColumnName(col_column_name);
                columnMeta.setTableSchema(table_schema);
                columnMeta.setTableName(col_table_name);

                columnMeta.setColumnKey(col_column_key);
                columnMeta.setDataType(col_data_type);
                columnMeta.setColumnComment(col_column_comment);

                columnMeta.setOrdinalPosition(col_ordinal_position);
                columnMeta.setColumnDefault(col_column_default);
                columnMeta.setIsNullable(col_is_nullable);

                // 输出数据
                columns.add(columnMeta);
                TableMeta table = tempMap.get(table_name);
                if (table == null) {
                    table = new TableMeta();
                }

                table.setColumns(columns);
                table.setTableComment(resultSet.getString("TABLE_COMMENT"));// 表注释
                table.setTableSchema(table_schema);
                // 转换为驼峰
                if (table_name != null) {
//                    NameUtil.upperCaseFirstWord()
                    String[] arr = table_name.split("_");
                    StringBuilder sb = new StringBuilder();
                    for (String s : arr) {
                        sb.append(NameUtil.upperCaseFirstWord(s));
                    }
                    table.setTableName(sb.toString());
                }
                tempMap.put(table_name, table);
            }

            // 完成后关闭
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            // 处理 JDBC 错误
            log.error("ERROR", exception);
        } catch (Exception exception) {
            // 处理 Class.forName 错误
            log.error("ERROR", exception);
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

        log.info("jdbc 结束={}",tempMap.size());
        return new ArrayList<>(tempMap.values());
    }


    @Override
    public List<TableMeta> listTableMateListBySQLStringList(List<String> createTableSQLList) {
        Assert.notNull(createTableSQLList, "获取表元元素异常,建表语句为空!");
        List<TableMeta> resp = new ArrayList<>(16);
        for (String sqlString : createTableSQLList) {
            resp.add(getTableMateBySQLString(sqlString));
        }
        return resp;
    }

    @Override
    public List<TableMeta> listTableMateListBySQLsString(String createTableSQLStrings) {
        Assert.notNull(createTableSQLStrings, "获取表元元素异常,建表语句为空(注意多sql时用英文分号分隔)!");
        String[] sqlStrings = createTableSQLStrings.split(";");
        Assert.notNull(sqlStrings, "获取表元元素异常,建表语句为空(注意多sql时用英文分号分隔)!");
        List<TableMeta> resp = new ArrayList<>(16);
        for (String sqlString : sqlStrings) {
            resp.add(getTableMateBySQLString(sqlString));
        }
        return resp;
    }

    @Override
    public TableMeta getTableMateBySQLString(String sqlString) {
        Assert.notNull(sqlString, "获取表元元素异常,建表语句为空!");
        // todo 解析sql到model
        return null;
    }


    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8";
        String user = "root";
        String password = "admin";
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String schemaName = "test";
        String tableName = "role";
        TableMeta tableMeta = new MetaServiceImpl().selectTableMateByJDBC(url, user, password, driverClassName, schemaName, tableName);
        log.info("表解析出的元元素={}", JSON.toJSONString(tableMeta));
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
