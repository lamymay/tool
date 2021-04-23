package com.arc.code.generator.service.impl;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.model.MockControl;
import com.arc.code.generator.model.OutTemplateConfig;
import com.arc.code.generator.model.TemplateValue;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.*;

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
    public ArcPropertiesProvider executeByContext(ArcPropertiesProvider arcContext) {

        // 1 参数校验与 元数据准备 --参数校验2-- 补充一些必要参数
        List<OutTemplateConfig> outTemplateConfigList = verifyAndPrepareParameter(arcContext);


        try {

            if (outTemplateConfigList == null || outTemplateConfigList.size() < 1) {
                throw new RuntimeException("无可输出的文件");
            }

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


    /**
     * 参数校验1
     * 简单把从系统配置文件中收集的配置参数转换为map
     *
     * @param arcContext ArcPropertiesProvider
     * @return Map
     */
    private List<OutTemplateConfig> verifyAndPrepareParameter(ArcPropertiesProvider arcContext) throws IOException {
        Assert.notNull(arcContext, "配置参数不能为空");


        // 获取表格数据 组装输出配置
        List<TableMeta> tableMetas = metaService.selectTableMateListOptimization(arcContext, arcContext.isUseProjectDefaultDataSource());
        if (tableMetas == null || tableMetas.size() < 1) {
            return Collections.emptyList();
        }


        // 全局设置 文件的作者
        arcContext.setAuthor(new ArcCodeGeneratorContext().getAuthor());

        // 全局设置 输出文件路径处理
        String output = arcContext.getOutput() != null ? arcContext.getOutput() : defaultOutputPath;
        if (!output.endsWith(File.separator)) {
            output = output + File.separator;
            arcContext.setOutput(output);
        }

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

            OutTemplateConfig outTemplateConfig = new OutTemplateConfig();

            // 1 data -- 合成模板用的参数
            Map<String, Object> parameterMap = new HashMap<>(16);
            parameterMap.put(ArcPropertiesProvider.class.getName(), arcContext);
            parameterMap.put("tableAlias", arcContext.getTableAlias());


            parameterMap.put("javaPackage", rootNamespace);
            parameterMap.put("rootNamespace", rootNamespace);

            outTemplateConfig.setData(parameterMap);

            // 2 todo获取模板
            outTemplateConfig.setTemplate(configuration.getTemplate(MockControl.templateName));
            outTemplateConfigList.add(outTemplateConfig);

            // 3 输出文件
            output+
            outTemplateConfig.setOutputFile(new File());
        }

        return outTemplateConfigList;
    }


    public Object process(OutTemplateConfig outTemplateConfig) {
        log.debug("模板合成,参数OutTemplateConfig={}", JacksonUtils.toJson(outTemplateConfig));

        Assert.notNull(outTemplateConfig, "模板合成错误,原因:模板配置为空");
        // String templateName = outTemplateConfig.getTemplateName();

        String outputFileFullName = outTemplateConfig.getOutputFileFullName();
        Object data = outTemplateConfig.getData();

        // 获取模板
        Template template = outTemplateConfig.getTemplate();
        Assert.notNull(templateName, "模板合成错误,原因:输出模板为空");
        Assert.notNull(outputFileFullName, "模板合成错误,原因:输出文件为空");
        Assert.notNull(data, "模板合成错误,原因:输出参数为空");



    }

    File outputFile = new File(outputFileFullName);
        if(!outputFile.exists())

    {
        //createNewFile这个方法只能在一层目录下创建文件，不能跳级创建，尽管可以用mkdir(s)创建多层不存在的目录，但是不要直接一个File对象搞定目录和文件都需要创建的情况，可以在已有目录下直接用createNewFile创建文件
        if (!outputFile.getParentFile().exists()) {
            boolean mkdirs = outputFile.getParentFile().mkdirs();
            String msg = "mkdirs 尝试创建父级路径结果= " + mkdirs;
            log.info(msg);
            if (!mkdirs) {
                throw new RuntimeException(msg);
            }
        }
        boolean result = false;
        try {
            result = outputFile.createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
            log.error("创建文件异常 createNewFile ", exception);
        }
        log.info("############################################");
        log.info("文件路径={}", outputFile.getPath());
        log.info("############################################");
        log.info("javaFile.createNewFile()={}", result);
    }

    FileWriter writer = null;
        try

    {
        writer = new FileWriter(outputFile);


//        Environment processingEnvironment = template.createProcessingEnvironment(parameterMap, writer, null);
        template.process(parameterMap, writer);
        writer.flush();

    } catch(
    IOException exception)

    {
        exception.printStackTrace();
    } catch(
    TemplateException e)

    {
        e.printStackTrace();
    } finally

    {
        try {
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
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

