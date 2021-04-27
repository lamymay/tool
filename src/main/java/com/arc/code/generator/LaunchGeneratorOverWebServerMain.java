package com.arc.code.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类--web Server方式『方案二』
 * 运行main方法启动web server，通过controller传入参数来生成代码，需要启动web容器
 * 部署：默认情况下：项目将打包为jar，使用内置tomcat启动，外部条件需要mysql8.0
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/10/3 8:01
 */
@Slf4j
@SpringBootApplication
//@EnableSwagger2ForArcApi
public class LaunchGeneratorOverWebServerMain {

    public static void main(String[] args) {
        SpringApplication.run(LaunchGeneratorOverWebServerMain.class, args);
    }

}

