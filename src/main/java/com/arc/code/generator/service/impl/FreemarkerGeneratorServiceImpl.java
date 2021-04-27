package com.arc.code.generator.service.impl;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.model.OutTemplateConfig;
import com.arc.code.generator.model.domain.meta.TableMeta;
import com.arc.code.generator.service.FreemarkerGeneratorService;
import com.arc.code.generator.service.MetaService;
import com.arc.code.generator.utils.JacksonUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.arc.code.generator.model.MockControl.defaultOutputPath;

/**
 * FreemarkerGeneratorServiceImpl
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/10/3 15:29
 */
@Service
public class FreemarkerGeneratorServiceImpl implements InitializingBean, FreemarkerGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(FreemarkerGeneratorServiceImpl.class);

//    /**
//     * 系统参数
//     */
//    @Autowired
//    @Qualifier("arcPropertiesProviderImpl1")
//    private ArcPropertiesProvider arcPropertiesProvider;

    /**
     * 模板工具配置
     */
    @Resource
    private Configuration configuration;

    /**
     * 表元元素获取
     */
    @Autowired
    private MetaService metaService;

    @Override
    public void afterPropertiesSet() {
        //准备输出目录
        log.info("######## info  afterPropertiesSet方法执行时刻={}", System.currentTimeMillis());
        log.debug("######## debug  afterPropertiesSet方法执行时刻={}", System.currentTimeMillis());
        log.warn("######## warn  afterPropertiesSet方法执行时刻={}", System.currentTimeMillis());
        log.trace("######## trace  afterPropertiesSet方法执行时刻={}", System.currentTimeMillis());
        log.error("######## error  afterPropertiesSet方法执行时刻={}", System.currentTimeMillis());
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


    @Override
    public ArcPropertiesProvider processByContext(ArcPropertiesProvider arcContext) {

        // 1 参数校验与 元数据准备 --参数校验2-- 补充一些必要参数
        List<OutTemplateConfig> outTemplateConfigList = verifyAndPrepare(arcContext);
        if (outTemplateConfigList == null || outTemplateConfigList.size() < 1) {
            throw new RuntimeException("无可输出的文件");
        }

        try {

            //2、模板数据合成输出到文件
            for (OutTemplateConfig outTemplateConfig : outTemplateConfigList) {
                process(outTemplateConfig);
            }

        } catch (Exception exception) {
            log.error("ERROR", exception);
            throw new RuntimeException(exception);
        }
        arcContext.setSuccess(true);
        return arcContext;
    }

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
     * 1参数校验 2参数准备
     * 把从 配置参数转换为 可以直接输出的数据
     *
     * @param arcContext ArcPropertiesProvider
     * @return Map
     */
    private List<OutTemplateConfig> verifyAndPrepare(ArcPropertiesProvider arcContext) {
        Assert.notNull(arcContext, "配置参数不能缺省");


        // 获取表格数据 组装输出配置
        List<TableMeta> tableMetas = metaService.selectTableMateListOptimization(arcContext, arcContext.isUseProjectDefaultDataSource());
        Assert.notNull(arcContext, "数据表的元数据获取失败");

        // 全局设置 输出文件路径处理
        String output = arcContext.getOutput() != null ? arcContext.getOutput() : defaultOutputPath;
        if (!output.endsWith(File.separator)) {
            output = output + File.separator;
            arcContext.setOutput(output);
        }

        // 全局设置 文件的作者
        arcContext.setAuthor(new ArcCodeGeneratorContext().getAuthor());

        // 全局设置 输出文件的根目录
        String rootNamespace = arcContext.getProjectProperties().getRootNamespace();


        List<OutTemplateConfig> outTemplateConfigList = new ArrayList<>(16);

        for (TableMeta tableMeta : tableMetas) {
            // 一张表生成 一套代码
            // model.java
            // mapper.java
            // mapper.xml
            // service.java
            // serviceImpl.java
            // controller.java
            // request.java
            // response.java


            String className = tableMeta.getClassName(arcContext.getRemovePrefix());

            // 一张表 输出一套 代码
            OutTemplateConfig outTemplateConfig = new OutTemplateConfig();

//            TemplateData dataModel = new TemplateData();
            // 表名称
//            dataModel.setTableName(tableMeta.getTableName());

            // 表的注释
//            dataModel.setTableComment(tableMeta.getTableComment());

//            dataModel.setTableAlias(arcContext.getTableAlias());
//            dataModel.setJavaPackage(rootNamespace);
//            dataModel.setRootNamespace(rootNamespace);


            // 1 data -- 合成模板用的参数  2模板名称 3输出文件名称

            tableMeta.setTableAlias(arcContext.getTableAlias());
//            tableMeta.setJavaPackage(rootNamespace);
            tableMeta.setRootNamespace(rootNamespace);
            tableMeta.setAuthor(arcContext.getAuthor());


            outTemplateConfig.setMeta(tableMeta);


            outTemplateConfig.setTemplateName("model.ftl");
            outTemplateConfigList.add(outTemplateConfig);

            // 3 输出文件
            outTemplateConfig.setOutputFileFullName(output + className + ".java");
        }

        return outTemplateConfigList;
    }


    public OutTemplateConfig process(OutTemplateConfig outTemplateConfig) {
        log.debug("模板合成,参数OutTemplateConfig={}", JacksonUtils.toJson(outTemplateConfig));
        Assert.notNull(outTemplateConfig, "模板合成错误,原因:模板配置为空");

        TableMeta data = outTemplateConfig.getMeta();
        String outputFileFullName = outTemplateConfig.getOutputFileFullName();

        Assert.notNull(outputFileFullName, "模板合成错误,原因:输出文件为空");
        Assert.notNull(data, "模板合成错误,原因:输出参数为空");


        File outputFile = createOutFile(outputFileFullName);

        FileWriter writer = null;
        try {
            writer = new FileWriter(outputFile);
            Template template = configuration.getTemplate(outTemplateConfig.getTemplateName());
            template.process(data, writer);
            //        log.debug("模板输出后返回processingEnvironment={}", processingEnvironment);
            writer.flush();
        } catch (IOException exception) {
            log.error("模板合成IO异常,", exception);
        } catch (TemplateException exception) {
            log.error("模板合成异常,", exception);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException exception) {
                log.error("模板合成异常,writer.close(),", exception);
            }

        }
        outTemplateConfig.setSuccess(true);
        return outTemplateConfig;
    }

    private File createOutFile(String outputFileFullName) {
        File outputFile = new File(outputFileFullName);
        if (!outputFile.exists()) {
            //createNewFile这个方法只能在一层目录下创建文件，不能跳级创建，尽管可以用mkdir(s)创建多层不存在的目录，但是不要直接一个File对象搞定目录和文件都需要创建的情况，可以在已有目录下直接用createNewFile创建文件
            if (!outputFile.getParentFile().exists()) {
                boolean mkdirs = outputFile.getParentFile().mkdirs();
                String msg = "输出文件创建过程中,创建父级路径" + (mkdirs ? "成功" : "失败");
                log.info(msg);
                if (!mkdirs) {
                    throw new RuntimeException(msg);
                }
            }
            try {
                boolean result = outputFile.createNewFile();
                log.info("输出文件创建成功={},文件路径={}", result, outputFile.getPath());
            } catch (IOException exception) {
                exception.printStackTrace();
                log.error("输出文件创建异常 createNewFile ", exception);
            }
        }
        return outputFile;
    }


}

