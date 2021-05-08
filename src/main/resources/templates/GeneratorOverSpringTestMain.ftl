package ${basePackage};

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


/**
* 启动类--『web Server方式启动项目然后测试』
* Attention:  will launch the web container.
* 运行main方法启动web server，通过controller传入参数来生成代码，需要启动web容器
* 部署：默认情况下：项目将打包为jar，使用内置tomcat启动，外部条件需要mysql8.0
*
* @author ${author?default("")}
* @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
*/
@MapperScan("${mapperPackage}")
@RestController
@SpringBootApplication
public class GeneratorOverSpringTestMain {
    private static final Logger log = LoggerFactory.getLogger(GeneratorOverSpringTestMain.class);

    public static void main(String[] args) {
       SpringApplication.run(GeneratorOverSpringTestMain.class, args);
    }

}



