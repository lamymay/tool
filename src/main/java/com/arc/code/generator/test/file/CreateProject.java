package com.arc.code.generator.test.file;

import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.config.template.ArcTemplateConfiguration;
import com.arc.code.generator.model.OutTemplateConfig;
import com.arc.code.generator.service.FreemarkerGeneratorService;
import com.arc.code.generator.service.impl.FreemarkerGeneratorServiceImpl;
import com.arc.code.generator.service.impl.MetaServiceImpl;
import freemarker.template.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.*;

import static com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext.getArcPropertiesProvider;

/**
 * 构造一个测试项目
 *
 * @author may
 * @since 2021/5/3 21:59
 */
public class CreateProject {

    private static final Logger log = LoggerFactory.getLogger(CreateProject.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        creatProject();
    }

    static AnnotationConfigApplicationContext context = null;

    private static void creatProject() throws IOException {
        long t1 = System.currentTimeMillis();
        //0、Spring容器环境配置
        context = new AnnotationConfigApplicationContext(
                MetaServiceImpl.class,
                ArcTemplateConfiguration.class,
                FreemarkerGeneratorServiceImpl.class
        );

        ArcTemplateConfiguration arcTemplateConfiguration = context.getBean(ArcTemplateConfiguration.class);
        Configuration configuration = context.getBean(Configuration.class);

        log.debug("arcTemplateConfiguration={}", arcTemplateConfiguration);
        log.debug("configuration={}", configuration);

        long t2 = System.currentTimeMillis();
        log.info("Spring容器环境配置耗时{}ms", (t2 - t1));

        //  1、获取配置参数
        ArcCodeGeneratorContext configContext = getArcPropertiesProvider();

        //  2、从Spring容器中获取生成工具bean   (干掉 mybatis 转而使用jdbc!)
        FreemarkerGeneratorService freemarkerGenerator = context.getBean(FreemarkerGeneratorService.class);

        // 3、输出文件到文件夹

        configContext.setOutputType(1);
        configContext.setOutput("E:\\free");

        List<OutTemplateConfig> templateConfigWithDataList = prepareOutTemplateData(configContext);
        for (OutTemplateConfig temp : templateConfigWithDataList) {
            freemarkerGenerator.process(temp);
        }


        //createDefaultBasePackage(configContext);


        // 结果输出 文件输出zip
        String outPath = configContext.getOutput();
        //        ZipFileUtil.outputFilesZip(output);

        try {
            File file = new File(outPath);
            Desktop.getDesktop().open(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            log.error("error", exception);
        }
        long t3 = System.currentTimeMillis();
        log.info("生成过程耗时{}ms", (t3 - t2));
    }

    private static List<OutTemplateConfig> prepareOutTemplateData(ArcCodeGeneratorContext configContext) {

        // 输出文件到文件夹
        // projectName
        // |--src
        //     |--main
        //     |   |--java
        //     |   |--resources
        //     |      |--mapper
        //     |      |--static
        //     |      |--templates
        //     |      |--config
        //     |         |--application.properties
        //     |         |--application.yml
        //     |         |--application-dev.yml
        //     |--test
        //        |--java
        // |--.gitignore
        // |--pom.xml


        //C:\Users\may\Desktop\ray\April\tool\src\main\resources\templates\ projectName\src\main\java
        // E:/free/unit_test

        //{output}/projectName/src/main/java
        //{output}/projectName/src/main/resources/
        //{output}/projectName/src/main/static/
        //{output}/projectName/src/main/templates/
        //{output}/projectName/src/main/config/
        //{output}/projectName/src/main/mapper
        //{output}/projectName/src/main/config/application-dev.yml
        //{output}/projectName/src/main/config/application.yml
        //{output}/projectName/src/main/resources/logback-spring.xml

        List<OutTemplateConfig> list = new ArrayList<>();


        String output = configContext.getOutput() + File.separator + projectName;


        // 最外层的文件 pom ignore文件
        list.add(new OutTemplateConfig("pomXml.ftl", output + File.separator + "pom.xml"));

        list.add(new OutTemplateConfig(".gitignore.ftl", output + File.separator + ".gitignore"));


        // 项目测试目录
        //{output}/projectName/src/test/java/resources
        createParentDir(output + File.separator + "src" + File.separator + "test" + File.separator + "java");
        createParentDir(output + File.separator + "src" + File.separator + "test" + File.separator + "resources");

        // 项目工作目录
        //{output}/projectName/src/main/main/java
        //{output}/projectName/src/main/main/resources/mapper
        //{output}/projectName/src/main/main/resources/static
        //{output}/projectName/src/main/main/resources/templates
//        createParentDir(output + File.separator + "src" + File.separator + "test" + File.separator + "java");
//        createParentDir(output + File.separator + "src" + File.separator + "test" + File.separator + "resources");
//
//        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper");
//        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static");
//        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "templates");


        //{output}/projectName/src/main/main/resources/config
        list.add(new OutTemplateConfig("application.ftl", output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config" + File.separator + "application.yml"));
        list.add(new OutTemplateConfig("application-dev.ftl", output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config" + File.separator + "application-dev.yml"));
        // 启动类
        Map<String, Object> data = new HashMap<>();
        data.put("rootNamespace", configContext.getClassFullName().getRootNamespace());
        data.put("createTime", new Date());
        list.add(new OutTemplateConfig("GeneratorOverSpringTestMain.ftl", output + File.separator + "src" + File.separator + "main" + File.separator + "java" + getFilePathByRootNamespace(configContext.getRootNamespace()) + File.separator + "GeneratorOverSpringTestMain.java", data));

        return list;
    }


    private static void createDefaultBasePackage(ArcCodeGeneratorContext configContext) throws IOException {

        String output = configContext.getOutput() + File.separator + projectName;

        log.debug("输出文件文件夹={}", output);


        // 父级路径是否存在

        createParentDir(output);

        String gitignoreFileName = ".gitignore";
        String pomXmlFileName = "pom.xml";
        File gitignoreFile = new File(output + File.separator + gitignoreFileName);
        File pomXmlFile = new File(output + File.separator + pomXmlFileName);
        boolean makeSuccess = false;
        if (!gitignoreFile.exists()) {
            makeSuccess = gitignoreFile.createNewFile();
            System.out.println("创建文件gitignoreFile结果=" + makeSuccess);
        }
        if (!pomXmlFile.exists()) {
            makeSuccess = pomXmlFile.createNewFile();
            System.out.println("创建文件pomXmlFile结果=" + makeSuccess);
        }

        // 文件拷贝
        copyByNio(new File("./.gitignore"), gitignoreFile);
        copyByNio(new File("./pom.xml"), pomXmlFile);

        //{output}/projectName/src/test/java/resources

        //{output}/projectName/src/main/main/java
        //{output}/projectName/src/main/main/resources/mapper
        //{output}/projectName/src/main/main/resources/static
        //{output}/projectName/src/main/main/resources/templates
        //{output}/projectName/src/main/main/resources/config


        createParentDir(output + File.separator + "src" + File.separator + "test" + File.separator + "java");
        createParentDir(output + File.separator + "src" + File.separator + "test" + File.separator + "resources");

        // todo
        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + getFilePathByRootNamespace(configContext.getRootNamespace()));

        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper");
        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static");
        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "templates");
        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config");

    }

    private static String getFilePathByRootNamespace(String rootNamespace) {
        if (StringUtils.isBlank(rootNamespace)) {
            return "";
        }

        StringBuffer outputPath = new StringBuffer();
        String[] split = rootNamespace.split("\\.");
        for (String everyPath : split) {
            outputPath.append(File.separator).append(everyPath);
        }
        return outputPath.toString();
    }

    private static boolean createParentDir(String output) {
        File rootFile = new File(output);
        if (rootFile.exists()) {
            log.info("输出文件临时文件夹={}", rootFile.getAbsoluteFile());
            return true;
        } else {
            boolean mkdirs = rootFile.mkdirs();
            log.info("输出文件临时文件夹不存在,尝试创建mkdirs={}", mkdirs ? "成功" : "失败");
            return mkdirs;
        }

    }

    static String projectName = "code";

    private static void createDefaultPOMXml(ArcCodeGeneratorContext configContext) {
        Integer outputType = configContext.getOutputType();
        // E:/free/unit_test
        String output = configContext.getOutput() + File.separator + projectName;

        switch (outputType) {
            case 1:
                log.debug("输出文件文件夹={}", output);


                // 文件拷贝
                String gitignoreFileName = ".gitignore";
                String pomXmlFileName = "pom.xml";

                // 父级路径是否存在
                File rootFile = new File(output);
                if (rootFile.exists()) {
                    log.info("输出文件临时文件夹={}", rootFile.getAbsoluteFile());
                } else {
                    boolean mkdirs = rootFile.mkdirs();
                    log.info("输出文件临时文件夹不存在,尝试创建mkdirs={}", mkdirs ? "成功" : "失败");
                }

                File gitignoreFile = new File(output + File.separator + gitignoreFileName);
                File pomXmlFile = new File(output + File.separator + pomXmlFileName);
                try {
                    boolean makeSuccess = false;
                    if (!gitignoreFile.exists()) {
                        makeSuccess = gitignoreFile.createNewFile();
                        System.out.println("创建文件gitignoreFile结果=" + makeSuccess);
                    }
                    if (!pomXmlFile.exists()) {
                        makeSuccess = pomXmlFile.createNewFile();
                        System.out.println("创建文件pomXmlFile结果=" + makeSuccess);
                    }


                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                copyByNio(new File("./.gitignore"), gitignoreFile);
                copyByNio(new File("./pom.xml"), pomXmlFile);


                // 文件夹压缩
                //ZipFileUtil.outputFilesToZip(output);

            default:
                return;
        }

    }


    //使用FileChannel Java NIO包括transferFrom方法,根据文档应该比文件流复制的速度更快。

    public static void copyByNio(File source, File dest) {
        FileChannel input = null;
        FileChannel output = null;

        try {
            input = new FileInputStream(source).getChannel();
            output = new FileOutputStream(dest).getChannel();
            output.transferFrom(input, 0, input.size());
        } catch (Exception e) {
            log.warn("copyByNio error occur while copy", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            }
        }
    }

    private static void createPOMXml(Dependency dependency, ArcCodeGeneratorContext configContext) throws IOException {

//        if (dependency == null) {
//            createDefault(configContext);
//        }

//        if (MYSQL_DATABASE_VERSION80.equals(dependency.getDatabaseVersion())) {
//            log.debug("MYSQL_DATABASE_VERSION80");
//        }
//        if (MYSQL_DATABASE_VERSION5X.equals(dependency.getDatabaseVersion())) {
//
//        }
    }

}


// 不易标准化
// 负责技术
//



































