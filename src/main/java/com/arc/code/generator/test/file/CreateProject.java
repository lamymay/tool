package com.arc.code.generator.test.file;

import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.config.template.ArcTemplateConfiguration;
import com.arc.code.generator.service.FreemarkerGeneratorService;
import com.arc.code.generator.service.impl.FreemarkerGeneratorServiceImpl;
import com.arc.code.generator.service.impl.MetaServiceImpl;
import com.arc.code.generator.utils.FileUtil;
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
import java.util.HashMap;
import java.util.Map;

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

        Object data = new Object();
        Map<File, String> map = prepareOutMap(configContext.getOutput());


        for (Map.Entry<File, String> entry : map.entrySet()) {
            freemarkerGenerator.outputFile(entry.getKey(), entry.getValue(), data);
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

    private static Map<File, String> prepareOutMap(String output) {
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
        Map<File, String> map = new HashMap<>();


        output = output + File.separator + projectName;
        String application_properties = output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config" + File.separator + "application.properties";
        System.out.println(application_properties);
        System.out.println(application_properties);
        System.out.println(application_properties);

        map.put(FileUtil.createOutFile(application_properties), "application_properties.ftl");
        map.put(FileUtil.createOutFile(output + File.separator + "application.properties"), "pom_xml.ftl");
        map.put(FileUtil.createOutFile(output + File.separator + ".gitignore"), ".gitignore.ftl");

        return map;
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
        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + builtFilePath(configContext.getRootNamespace()));

        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper");
        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static");
        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "templates");
        createParentDir(output + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config");

    }

    private static String builtFilePath(String rootNamespace) {
        if (StringUtils.isBlank(rootNamespace)) {
            return "";
        }

        StringBuffer outputPath = new StringBuffer();
        for (String everyPath : rootNamespace.split(".")) {
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

    static String projectName = "projectName";

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



































