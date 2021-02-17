package com.arc.code.generator.service.impl;

import com.alibaba.fastjson.JSON;
import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.mapper.MetaMapper;
import com.arc.code.generator.model.domain.meta.ColumnMeta;
import com.arc.code.generator.model.domain.meta.TableMeta;
import com.arc.code.generator.service.FreemarkerGeneratorService;
import com.arc.code.generator.utils.JacksonUtils;
import com.arc.code.generator.utils.NameUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.*;

/**
 * FreemarkerGeneratorServiceImpl
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/10/3 15:29
 */
@Service
public class FreemarkerGeneratorServiceImpl implements InitializingBean, FreemarkerGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(FreemarkerGeneratorServiceImpl.class);

    private static final String modelFtl = "model.ftl";
    private static final String requestFtl = "request.ftl";
    private static final String responseFtl = "response.ftl";

    private static final String controllerFtl = "controller.ftl";
    private static final String serviceImplFtl = "serviceImpl.ftl";
    private static final String serviceFtl = "service.ftl";

    private static final String mapperInterfaceFtl = "mapperInterface.ftl";
    private static final String mapperXmlFtl = "mapperXml.ftl";
    private static final String JAVA_FILE_SUFFIX = ".java";


    /**
     * 系统参数
     */
    @Autowired
    @Qualifier("arcPropertiesProviderImpl1")
    private ArcPropertiesProvider arcPropertiesProvider;

    /**
     * 元数据查询提供
     */
    @Resource
    private MetaMapper mapper;

    /**
     * 模板工具配置
     */
    @Resource
    private Configuration configuration;

    @Override
    public void afterPropertiesSet() throws Exception {
        //准备输出目录
        log.info("afterPropertiesSet方法执行时刻={}", System.currentTimeMillis());
        //        dataModel = new HashMap<>(40);
        //        File curDir = new File(this.getClass().getClassLoader().getResource(".").getPath());
        //        File outDir = new File(curDir.getParent() + "/output");
        //        if (!outDir.exists()) {
        //            outDir.mkdirs();
        //        }
        //        //T:\Project\Za\tool\target\output
        //        output = outDir.getPath();
        //
        //        System.out.println(output);
        //        System.out.println(output);
        //        String tableAlias = generatorProperties.getDatabase().getTableAlias();
        //        if (!tableAlias.endsWith("_")) {
        //            tableAlias += "_";
        //        }
    }


    /**
     * 创建 model 文件
     * 创建 mapper接口文件
     * 创建 mapper.xml文件
     * 创建 service 文件
     * 创建 service.impl 文件
     * 创建 controller 文件
     *
     * @param propertiesProvider
     * @return
     */
    @Override
    public Map<String, Object> execute(ArcPropertiesProvider propertiesProvider) {
        Map<String, Object> verifyAndPrepareParameter = verifyAndPrepareParameter(propertiesProvider);

        Map<String, Object> parameterAndExecuteResultMap = executeByMap(verifyAndPrepareParameter);
        return parameterAndExecuteResultMap;
    }


    /**
     * 参数使用map来传递
     *
     * @param parameterMap 参数
     * @return
     */
    @Override
    public Map<String, Object> executeByMap(Map<String, Object> parameterMap) {
        //1、参数校验与准备--参数校验2-- 补充一些必要参数
        Assert.notNull(parameterMap, "配置参数不能为空");
        //文件输出路径


        String output = (String) parameterMap.get("output");
        if (!output.endsWith(File.separator)) {
            output = output + File.separator;
        }
        Object output1 = parameterMap.put("output", output);
        log.debug("[校验前输出文件夹路径]output1={}", output1);
        log.debug("[校验后输出文件夹路径]output={}", output);
        log.info("File.separators是={},输出文件是路径output={}", File.separator, output);

        //元数据准备
        TableMeta meta = selectTableMate(parameterMap);
        parameterMap.put("meta", meta);
        parameterMap.put("className", meta.getClassName());
        parameterMap.put("lowerCaseFirstWordClassName", meta.getLowerCaseFirstWordClassName());
        parameterMap.put(TableMeta.class.getName(), meta);


        //0、打日志
        log.info("execute 方法执行时刻={}", System.currentTimeMillis());
        try {

            //2、创建数据结合模板输出到文件--输出文件夹

            //类名称
            String className = (String) parameterMap.get("className");
            String modelOutputFileName = output + className + ".java";
            String requestOutputFileName = output + className + "Request.java";
            String responseOutputFileName = output + className + "Response.java";
            String mapperInterfaceOutputFileName = output + className + "Mapper.java";
            String mapperXmlOutputFileName = output + className + "Mapper.xml";
            String serviceOutputFileName = output + className + "Service.java";
            String serviceImplOutputFileName = output + className + "ServiceImpl.java";
            String controllerOutputFileName = output + className + "Controller.java";

            // --------------- create model：参数+模板+输出位置绝对路径
            log.info("创建 model 文件");
            log.info("创建 mapper接口文件");
            log.info("创建 mapper.xml文件");

            process(parameterMap, modelFtl, modelOutputFileName);
            process(parameterMap, mapperXmlFtl, mapperXmlOutputFileName);
            process(parameterMap, mapperInterfaceFtl, mapperInterfaceOutputFileName);

            //是否需要生成 ：requestFtl、serviceFtl、serviceImplFtl、controllerFtl

            boolean onlyMapperAndXml = false;
            Object mapperAndXml = parameterMap.get("onlyModelMapperAndXml");
            if (mapperAndXml instanceof String) {
                onlyMapperAndXml = Boolean.valueOf((String) mapperAndXml);
            } else if (mapperAndXml instanceof Boolean) {
                onlyMapperAndXml = (Boolean) mapperAndXml;
            }


            log.info("仅仅输出mapper接口与xml={}", onlyMapperAndXml);
            if (!onlyMapperAndXml) {
                process(parameterMap, requestFtl, requestOutputFileName);
                process(parameterMap, serviceFtl, serviceOutputFileName);
                process(parameterMap, serviceImplFtl, serviceImplOutputFileName);
                process(parameterMap, controllerFtl, controllerOutputFileName);
            }

        } catch (Exception e) {
            log.debug("##############################################################");
            e.printStackTrace();
            log.error("e ={}", e);
            parameterMap.put("exception", e);
            parameterMap.put("result", false);
            log.error("配置参数可能出错了={}", JSON.toJSONString(parameterMap));
            log.debug("##############################################################");
            return parameterMap;
        }
        parameterMap.put("result", true);
        return parameterMap;
    }


    /**
     * 参数校验1
     * 简单把从系统配置文件中收集的配置参数转换为map
     *
     * @param propertiesProvider
     * @return
     */
    private Map<String, Object> verifyAndPrepareParameter(ArcPropertiesProvider propertiesProvider) {
        Map<String, Object> parameterMap = new HashMap<>();
        if (propertiesProvider == null) {
            propertiesProvider = arcPropertiesProvider;
        }

        //构造不同文件的包名
        String rootNamespace = propertiesProvider.getProjectProperties().getRootNamespace();
        parameterMap.put("rootNamespace", rootNamespace);
        parameterMap.put("modelNamespace", rootNamespace + ".model");
        parameterMap.put("requestNamespace", rootNamespace + ".request");
        parameterMap.put("mapperNamespace", rootNamespace + ".mapper");
        parameterMap.put("serviceNamespace", rootNamespace + ".service");
        parameterMap.put("serviceImplNamespace", rootNamespace + ".impl");
        parameterMap.put("controllerNamespace", rootNamespace + ".controller");


        //文件输出路径-- 有后置检验
        parameterMap.put("output", propertiesProvider.getOutput());
        parameterMap.put("onlyModelMapperAndXml", propertiesProvider.getOnlyModelMapperAndXml());
        parameterMap.put("javaPackage", arcPropertiesProvider.getProjectProperties().getRootNamespace());

        parameterMap.put("createTime", new Date());
        parameterMap.put("currentTimeMillis", System.currentTimeMillis());
        parameterMap.put(ArcPropertiesProvider.class.getName(), propertiesProvider);

        String tableAlias = arcPropertiesProvider.getTableAlias();
        if (!tableAlias.endsWith("_")) {
            tableAlias += "_";
        }
        parameterMap.put("tableAlias", tableAlias);

        return parameterMap;
    }

    /**
     * 获取表的元数据
     *
     * @param useCustomizeDataSourceByControllerReceived
     * @return
     */
    private TableMeta selectTableMate(Map<String, Object> useCustomizeDataSourceByControllerReceived) {
        Object useCustomizeDataSource = useCustomizeDataSourceByControllerReceived.get("useCustomizeDataSourceByControllerReceived");

        boolean useJdbc = false;
        if (useCustomizeDataSource instanceof String) {
            useJdbc = Boolean.valueOf((String) useCustomizeDataSourceByControllerReceived.get("useCustomizeDataSourceByControllerReceived"));
        } else if (useCustomizeDataSource instanceof Boolean) {
            useJdbc = (Boolean) useCustomizeDataSourceByControllerReceived.get("useCustomizeDataSourceByControllerReceived");
        }
        if (useJdbc) {
            return selectTableMateByCustomizeJdbc(useCustomizeDataSourceByControllerReceived);

        } else {
            return selectTableMateByDefaultDataSource();
        }

    }

    public float mysqlVersion = 8.0f;

    private String selectForTableMate = "SELECT " +
            "  T.TABLE_SCHEMA AS TABLE_SCHEMA, " +
            "  T.TABLE_NAME AS TABLE_NAME, " +
            "  T.TABLE_COMMENT AS TABLE_COMMENT, " +
            "  C.TABLE_SCHEMA AS COL_TABLE_SCHEMA, " +
            "  C.TABLE_NAME AS COL_TABLE_NAME, " +
            "  C.COLUMN_NAME AS COL_COLUMN_NAME, " +
            "  C.COLUMN_KEY AS COL_COLUMN_KEY, " +
            "  C.DATA_TYPE AS COL_DATA_TYPE, " +
            "  C.COLUMN_COMMENT AS COL_COLUMN_COMMENT, " +
            "  C.ORDINAL_POSITION AS COL_ORDINAL_POSITION, " +
            "  C.COLUMN_DEFAULT AS COL_COLUMN_DEFAULT, " +
            "  C.IS_NULLABLE AS COL_IS_NULLABLE  " +
            "FROM " +
            "  information_schema.`TABLES` T " +
            "  INNER JOIN information_schema.COLUMNS C ON T.TABLE_SCHEMA = C.TABLE_SCHEMA  " +
            "  AND T.TABLE_NAME = C.TABLE_NAME  " +
            "WHERE " +
            "  T.TABLE_SCHEMA = ?  " +
            "  AND T.TABLE_NAME = ? ";

    /**
     * jdbc 连接mysql获取数据
     *
     * @return
     */
    private TableMeta selectTableMateByCustomizeJdbc(Map<String, Object> parameterMap) {
        Connection connection = null;
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        TableMeta tableMeta = null;

        try {
            String jdbcDriver = (String) parameterMap.get("driverClassName");
            String username = (String) parameterMap.get("username");
            String password = (String) parameterMap.get("password");
            String dbUrl = (String) parameterMap.get("url");
            String schemaName = (String) parameterMap.get("schemaName");
            String tableName = (String) parameterMap.get("tableName");


            // 注册 JDBC 驱动
            Class.forName(jdbcDriver);

            // 打开链接
            log.info("连接数据库....");
            connection = DriverManager.getConnection(dbUrl, username, password);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            preparedStatement = connection.prepareStatement(selectForTableMate);
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

    //---------------------通用的


    /**
     * 获取表的元数据
     * 通过系统配置在yml或properties文件中的数据源来获取
     *
     * @return 表的元数据
     */
    private TableMeta selectTableMateByDefaultDataSource() {
        TableMeta meta = mapper.get(arcPropertiesProvider.getDatabaseProperties().getSchemaName(), arcPropertiesProvider.getDatabaseProperties().getTableName());
        if (meta == null) {
            throw new IllegalArgumentException("\n失败！发生错误！指定的表不存在，请检查表的名称或数据库是否配置正确！\nFailure! Please check schemaName and tableName are correct. ");
        }
        //缓存数据
        //cacheTableMeta(meta);
        return meta;
    }


    /**
     * 缓存数据
     *
     * @param meta
     */
    private void cacheTableMeta(TableMeta meta) {
        log.info("缓存数据{}", meta);
        log.debug("generatorPropertiesProvider.getDatabase().getSchemaName() 结果={}", arcPropertiesProvider.getDatabaseProperties().getSchemaName());
        log.debug("generatorPropertiesProvider.getDatabase().getTableName() 结果={}", arcPropertiesProvider.getDatabaseProperties().getTableName());
        log.debug("mapper 结果={}", mapper);
        log.debug("TableMeta 结果={}", meta);
        try {
            File file = new File("T:\\Project\\Za\\tool\\src\\main\\resources\\templates\\serializable\\SerializableTableMeta.txt");
            //创建父级目录
            System.out.println(file.getParent());
            String parent = file.getParent();
            File parentFile = new File(parent);
            System.out.println(parentFile.exists());


            if (!parentFile.exists()) {
                boolean mkdirs = parentFile.mkdirs();
                if (mkdirs == false) {
                    throw new RuntimeException("临时文件父级路径创建失败");
                }
            }

//            if (!parentFile.isDirectory()) {
//                boolean mkdirs = parentFile.mkdirs();
//                if (mkdirs == false) {
//                    throw new RuntimeException("临时文件父级路径创建失败");
//                }
//            }
            System.out.println("parentFile 是目录？" + parentFile.isDirectory());
            System.out.println("file.getPath()" + file.getPath());
            System.out.println("file.getName() " + file.getName());
            System.out.println("文件是个目录？ " + file.isDirectory());

            if (!file.exists()) {
//                boolean mkdirs = file.mkdirs();
//                if (mkdirs == false) {
//                    throw new RuntimeException("临时文件父级路径创建失败");
//                }
                boolean newFile = file.createNewFile();
                if (newFile == false) {
                    throw new RuntimeException("临时文件创建失败");
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = null;
            objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(meta);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (meta == null) {
            throw new IllegalArgumentException("\n指定的表不存在，请检查表的名称或数据库是否配置正确！\nPlease check schemaName and tableName are correct. ");
        }
        log.info("表的元信息为{}", JacksonUtils.toJson(meta));
    }


    /**
     * 全名？
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    private File forceCreateFile(String fileName) throws IOException {
        log.debug("文件名称={}", fileName);
        //output + File.separator + fileName
        File newFile = new File(fileName);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        return newFile;
    }


    /**
     * @param parameterMap
     * @param templateName
     * @param outputFileFullName
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public Object process(Map<String, Object> parameterMap, String templateName, String outputFileFullName) throws IOException, TemplateException {
        if (parameterMap == null) {
            parameterMap = new HashMap<>(1);
            parameterMap.put("result", false);
        }
        //todo 数据与模板合成 并输出
        log.debug("参数 parameterMap ={}", JacksonUtils.toJson(parameterMap));
        log.debug("Freemarker configuration ={},templateName={},outputFileFullName={}", configuration, templateName, outputFileFullName);
        Template template = configuration.getTemplate(templateName);

        File outputFile = new File(outputFileFullName);
        if (!outputFile.exists()) {
            //createNewFile这个方法只能在一层目录下创建文件，不能跳级创建，尽管可以用mkdir(s)创建多层不存在的目录，但是不要直接一个File对象搞定目录和文件都需要创建的情况，可以在已有目录下直接用createNewFile创建文件
            if (!outputFile.getParentFile().exists()) {
                boolean mkdirs = outputFile.getParentFile().mkdirs();
                String msg = "mkdirs 尝试创建父级路径结果= " + mkdirs;
                log.info(msg);
                if (!mkdirs) {
                    throw new RuntimeException(msg);
                }
            }
            boolean result = outputFile.createNewFile();
            log.info("############################################");
            log.info("文件路径={}", outputFile.getPath());
            log.info("############################################");
            log.info("javaFile.createNewFile()={}", result);
        }

        FileWriter writer = new FileWriter(outputFile);
//        Environment processingEnvironment = template.createProcessingEnvironment(parameterMap, writer, null);
        template.process(parameterMap, writer);
        writer.flush();
        writer.close();
        return parameterMap;
//        log.debug("模板输出后返回processingEnvironment={}", processingEnvironment);
    }


    //--------------------------------------test --model
    private void generateStandardModel(Map<String, Object> parameterMap) throws Exception {
        log.debug("Freemarker configuration ={}", configuration);
        log.debug("参数 parameterMap ={}", JacksonUtils.toJson(parameterMap));

        Template template = configuration.getTemplate("model.ftl");
        log.info("Use template file: {}. ", template.getName());


        //输出文件处理
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(".");
        //target  目录
        String path = new File(resource.getPath()).getParent();
        //todo 参数校验    target +  传入参数
        path = path + File.separator + ((ArcPropertiesProvider) parameterMap.get(ArcPropertiesProvider.class.getName())).getProjectProperties().getOutputFolder() + File.separator;

        TableMeta tableMeta = (TableMeta) parameterMap.get(TableMeta.class.getName());
        String className = tableMeta.getClassName();
        String newFilePath = path + className + JAVA_FILE_SUFFIX;
        parameterMap.put("output", newFilePath);
        log.debug("文件名称={}", newFilePath);
        File javaFile = new File(newFilePath);
        if (!javaFile.exists()) {
            //createNewFile这个方法只能在一层目录下创建文件，不能跳级创建，尽管可以用mkdir(s)创建多层不存在的目录，但是不要直接一个File对象搞定目录和文件都需要创建的情况，可以在已有目录下直接用createNewFile创建文件
            if (!javaFile.getParentFile().exists()) {
                boolean mkdirs = javaFile.getParentFile().mkdirs();
                log.debug("父级路径创建结果={}", mkdirs);
            }

            boolean result = javaFile.createNewFile();
            log.info("javaFile.createNewFile()={}", result);

        }
        template.process(parameterMap, new FileWriter(javaFile));

    }
}

