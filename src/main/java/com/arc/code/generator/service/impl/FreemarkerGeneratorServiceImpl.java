package com.arc.code.generator.service.impl;

import com.alibaba.fastjson.JSON;
import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.model.ClassFullName;
import com.arc.code.generator.model.OutTemplateConfig;
import com.arc.code.generator.model.domain.TableMeta;
import com.arc.code.generator.service.FreemarkerGeneratorService;
import com.arc.code.generator.service.MetaService;
import com.arc.code.generator.utils.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    @Autowired
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

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
    }


    @Override
    public ArcCodeGeneratorContext processByContext(ArcCodeGeneratorContext arcContext) {

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

    /**
     * 1参数校验 2参数准备
     * 把从 配置参数转换为 可以直接输出的数据
     *
     * @param arcContext ArcPropertiesProvider
     * @return Map
     */
    private List<OutTemplateConfig> verifyAndPrepare(ArcCodeGeneratorContext arcContext) {
        Assert.notNull(arcContext, "配置参数不能缺省");

        // 获取表格数据 组装输出配置
        List<TableMeta> tableMetas = metaService.selectListOptimization(arcContext);
        Assert.notNull(arcContext, "数据表的元数据获取失败");

        // 全局设置 输出文件路径处理
        String output = arcContext.getOutput() != null ? arcContext.getOutput() : defaultOutputPath;
        if (!output.endsWith(File.separator)) {
            output = output + File.separator;
            arcContext.setOutput(output);
        }

        // 全局设置 文件的作者
        //arcContext.setAuthor(new com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext().getAuthor());

        // 全局设置 输出文件的根目录
        List<OutTemplateConfig> outTemplateConfigList = new ArrayList<>(16);

        // 一张表生成 一套代码   如何控制需要生成哪些文件???
        for (TableMeta tableMeta : tableMetas) {
            // 1 data -- 合成模板用的参数  2模板名称 3输出文件名称
            List<OutTemplateConfig> outputConfigList = builtConfigs(tableMeta, arcContext);
            outTemplateConfigList.addAll(outputConfigList);
        }

        return outTemplateConfigList;
    }

    final private List<OutTemplateConfig> builtConfigs(final TableMeta tableMeta, final ArcCodeGeneratorContext configContext) {
        if (tableMeta == null || configContext == null) {
            throw new RuntimeException("准备输出模板参数时候必要数据为指定");
            //   String className = tableMeta.getClassName(configContext.getRemovePrefix());
        }

        // 1 最全的
        Map<String, String> javaSuffixAndTemplateNameMap = getJavaSuffixAndTemplateNameListByGenerateType(configContext.getGenerateType());
        // 一张表 输出一套 代码
        List<OutTemplateConfig> configList = new ArrayList<>();

        final String author = configContext.getAuthor();
        final String output = configContext.getOutput();

        final String className = tableMeta.getClassName(configContext.getRemovePrefix());
        for (Map.Entry<String, String> suffixAndTemplateName : javaSuffixAndTemplateNameMap.entrySet()) {

            if (suffixAndTemplateName == null
                    || suffixAndTemplateName.getKey() == null
                    || suffixAndTemplateName.getValue() == null) {
                log.error("错误,准备输出模板参数时,模板名称不可为空,entry=" + JSON.toJSONString(suffixAndTemplateName) + "|configContext={}" + JSON.toJSONString(configContext));
                continue;
            }

            OutTemplateConfig outTemplateConfig = new OutTemplateConfig();
            tableMeta.setTableAlias(configContext.getTableAlias());
            tableMeta.setAuthor(author);

            // 设置根路径
            tableMeta.setRootNamespace(configContext.getRootNamespace());
            ClassFullName classFullName = new ClassFullName(className, configContext);

            tableMeta.setClassFullName(classFullName);

            // mapper 名称
            tableMeta.setMapperName(getMapperNamespace(configContext, className));

            // 获取模板
            outTemplateConfig.setTemplateFileName(suffixAndTemplateName.getValue());
            outTemplateConfig.setMeta(tableMeta);
            outTemplateConfig.setOutputFileFullName(output + className + suffixAndTemplateName.getKey());

            configList.add(outTemplateConfig);
        }

        if (log.isDebugEnabled()) {
            //log.debug("准备输出模板参数如下:", JSON.toJSONString(configList));
        }
        return configList;
    }

    private String getMapperNamespace(ArcCodeGeneratorContext configContext, String className) {
        if (configContext != null && configContext.getClassFullName() != null
                && StringUtils.isNotBlank(configContext.getClassFullName().getMapperNamespace())) {
            return configContext.getClassFullName().getMapperNamespace().trim();
        }
        if (configContext != null && StringUtils.isNotBlank(configContext.getRootNamespace())) {
            return configContext.getRootNamespace() + className;
        }
        throw new RuntimeException("参数配置错误,mapper接口名称没有指定");
    }


    /**
     * 根据类型配置需要准备的模板
     *
     * @param generateType 输出方案类型
     * @return 模板名称集合
     */
    private Map<String, String> getJavaSuffixAndTemplateNameListByGenerateType(int generateType) {

        // 说明 key是Java文件的后缀  value是模板文件全名
        Map<String, String> javaSuffixAndTemplateName = new HashMap<>();
        switch (generateType) {
            // only model
            case 0:
                javaSuffixAndTemplateName.put("Model.java", "model.ftl");
                break;
            // only xml
            case 1:
                javaSuffixAndTemplateName.put("Mapper.xml", "mapperXml.ftl");
                break;
            // DAO
            case 3:
                javaSuffixAndTemplateName.put("Model.java", "model.ftl");
                javaSuffixAndTemplateName.put("Mapper.xml", "mapperXml.ftl");
                break;

            // DAO service
            case 4:
                // DAO Service controller
            case 6:
                // DAO Service Controller req resp
            case 8:
                //21 特殊定制模式 : 如控制输出方法名称

            case 91:
                javaSuffixAndTemplateName.put("Model.java", "model.ftl");
                javaSuffixAndTemplateName.put("Mapper.xml", "mapperXml.ftl");
                javaSuffixAndTemplateName.put("MapperInterface.java", "mapperInterface.ftl");

                javaSuffixAndTemplateName.put("Service.java", "service.ftl");
                javaSuffixAndTemplateName.put("ServiceImpl.java", "serviceImpl.ftl");
                javaSuffixAndTemplateName.put("Controller.java", "controller.ftl");
                javaSuffixAndTemplateName.put("Request.java", "request.ftl");
                javaSuffixAndTemplateName.put("Response.java", "response.ftl");

                break;

            // default 完整输出模式
            default:

                javaSuffixAndTemplateName.put("Model.java", "model.ftl");
                javaSuffixAndTemplateName.put("Mapper.xml", "mapperXml.ftl");
                javaSuffixAndTemplateName.put("MapperInterface.java", "mapperInterface.ftl");

                javaSuffixAndTemplateName.put("Service.java", "service.ftl");
                javaSuffixAndTemplateName.put("ServiceImpl.java", "serviceImpl.ftl");
                javaSuffixAndTemplateName.put("Controller.java", "controller.ftl");
                javaSuffixAndTemplateName.put("Request.java", "request.ftl");
                javaSuffixAndTemplateName.put("Response.java", "response.ftl");

        }
        return javaSuffixAndTemplateName;
    }


    @Override
    public void process(OutTemplateConfig outTemplateConfig) {

        //public void outputFile(String , String outputFileFullName, Object data) {
        //log.debug("模板合成,参数OutTemplateConfig={}", JacksonUtils.toJson(outTemplateConfig));
        Assert.notNull(outTemplateConfig, "模板合成错误,原因:模板配置为空");

        String templateName = outTemplateConfig.getTemplateFileName();
        String outputFileFullName = outTemplateConfig.getOutputFileFullName();
        Object data = outTemplateConfig.getMeta();
        Assert.notNull(outputFileFullName, "模板合成错误,原因:输出文件为空");
        Object temp = outTemplateConfig.getData();
        if (temp != null) {
            data = temp;
        }
        //Assert.notNull(data, "模板合成错误,原因:输出参数为空");

        FileWriter writer = null;
        try {
            writer = new FileWriter(FileUtil.createOutFile(outputFileFullName));
            Template template = configuration.getTemplate(templateName);
            template.process(data, writer);
            //        log.debug("模板输出后返回processingEnvironment={}", processingEnvironment);
            writer.flush();
        } catch (IOException exception) {
            log.error("ERROR,模板合成IO异常,", exception);
        } catch (TemplateException exception) {
            log.error("ERROR,模板合成异常,", exception);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException exception) {
                log.error("模板合成异常,writer.close(),", exception);
            }

        }
    }


}

