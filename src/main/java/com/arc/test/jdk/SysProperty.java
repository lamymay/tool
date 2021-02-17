package com.arc.test.jdk;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @description:
 * @author: yechao
 * @date: 2018/10/16 19:19
 */
public class SysProperty {

    public static void main(String[] args) {

        System.out.println("java版本号：" + System.getProperty("java.version")); // java版本号
        System.out.println("Java提供商名称：" + System.getProperty("java.vendor")); // Java提供商名称
        System.out.println("Java提供商网站：" + System.getProperty("java.vendor.url")); // Java提供商网站
        System.out.println("jre目录：" + System.getProperty("java.home")); // Java，哦，应该是jre目录
        System.out.println("Java虚拟机规范版本号：" + System.getProperty("java.vm.specification.version")); // Java虚拟机规范版本号
        System.out.println("Java虚拟机规范提供商：" + System.getProperty("java.vm.specification.vendor")); // Java虚拟机规范提供商
        System.out.println("Java虚拟机规范名称：" + System.getProperty("java.vm.specification.name")); // Java虚拟机规范名称
        System.out.println("Java虚拟机版本号：" + System.getProperty("java.vm.version")); // Java虚拟机版本号
        System.out.println("Java虚拟机提供商：" + System.getProperty("java.vm.vendor")); // Java虚拟机提供商
        System.out.println("Java虚拟机名称：" + System.getProperty("java.vm.name")); // Java虚拟机名称
        System.out.println("Java规范版本号：" + System.getProperty("java.specification.version")); // Java规范版本号
        System.out.println("Java规范提供商：" + System.getProperty("java.specification.vendor")); // Java规范提供商
        System.out.println("Java规范名称：" + System.getProperty("java.specification.name")); // Java规范名称
        System.out.println("Java类版本号：" + System.getProperty("java.class.version")); // Java类版本号
        System.out.println("Java类路径：" + System.getProperty("java.class.path")); // Java类路径
        System.out.println("Java lib路径：" + System.getProperty("java.library.path")); // Java lib路径
        System.out.println("Java输入输出临时路径：" + System.getProperty("java.io.tmpdir")); // Java输入输出临时路径
        System.out.println("Java编译器：" + System.getProperty("java.compiler")); // Java编译器
        System.out.println("Java执行路径：" + System.getProperty("java.ext.dirs")); // Java执行路径
        System.out.println("操作系统名称：" + System.getProperty("os.name")); // 操作系统名称
        System.out.println("操作系统的架构：" + System.getProperty("os.arch")); // 操作系统的架构
        System.out.println("操作系统版本号：" + System.getProperty("os.version")); // 操作系统版本号
        System.out.println("文件分隔符：" + System.getProperty("file.separator")); // 文件分隔符
        System.out.println("路径分隔符：" + System.getProperty("path.separator")); // 路径分隔符
        System.out.println("直线分隔符：" + System.getProperty("line.separator")); // 直线分隔符
        System.out.println("操作系统用户名：" + System.getProperty("user.name")); // 用户名
        System.out.println("操作系统用户的主目录：" + System.getProperty("user.home")); // 用户的主目录
        System.out.println("当前程序所在目录：" + System.getProperty("user.dir")); // 当前程序所在目录

    }



    private String getProjectRootPath() {
        String path = "";
        try {


            System.out.println("-------------------------------");

            // 第一种：获取类加载的根路径   C:\Users\X\Desktop\Zan\git\arc\server-db\jpa-tool\target\classes
            File f = new File(this.getClass().getResource("/").getPath());
            System.out.println(f);

            // 第二种：获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  C:\Users\X\Desktop\Zan\git\arc\server-db\jpa-tool\target\classes\com\arc\model
            File f2 = new File(this.getClass().getResource("").getPath());
            System.out.println(f2);


            // 第三种：获取项目路径    C:\Users\X\Desktop\Zan\git\arc
            File directory = new File("");// 参数为空
            String courseFile = null;
            courseFile = directory.getCanonicalPath();
            System.out.println(courseFile);


            // 第四种：获取类加载的根路径注意前面有"file:/"   file:/C:/Users/X/Desktop/Zan/git/arc/server-db/jpa-tool/target/classes/
            URL xmlpath = this.getClass().getClassLoader().getResource("");//等价 Thread.currentThread().getContextClassLoader().getResource("");
            System.out.println(xmlpath);


            // 第五种：项目的根路径  C:\Users\X\Desktop\Zan\git\arc
            System.out.println(System.getProperty("user.dir"));//用户的当前工作目录

            // 第六种：  获取当前工程路径获取所有的类路径 包括jar包的路径
            System.out.println(System.getProperty("java.class.path"));//C:\Java\jdk1.8.0_171\jre\lib\charsets.jar;C:\Java\jdk1.8.0_171\jre\lib\deploy.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\access-bridge-64.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\cldrdata.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\dnsns.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\jaccess.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\jfxrt.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\localedata.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\nashorn.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\sunec.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\sunjce_provider.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\sunmscapi.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\sunpkcs11.jar;C:\Java\jdk1.8.0_171\jre\lib\ext\zipfs.jar;C:\Java\jdk1.8.0_171\jre\lib\javaws.jar;C:\Java\jdk1.8.0_171\jre\lib\jce.jar;C:\Java\jdk1.8.0_171\jre\lib\jfr.jar;C:\Java\jdk1.8.0_171\jre\lib\jfxswt.jar;C:\Java\jdk1.8.0_171\jre\lib\jsse.jar;C:\Java\jdk1.8.0_171\jre\lib\management-agent.jar;C:\Java\jdk1.8.0_171\jre\lib\plugin.jar;C:\Java\jdk1.8.0_171\jre\lib\resources.jar;C:\Java\jdk1.8.0_171\jre\lib\rt.jar;C:\Users\X\Desktop\Zan\git\arc\server-db\jpa-tool\target\classes;C:\Users\X\.m2\repository\org\springframework\spring-context\5.0.7.RELEASE\spring-context-5.0.7.RELEASE.jar;C:\Users\X\.m2\repository\org\springframework\spring-aop\5.0.7.RELEASE\spring-aop-5.0.7.RELEASE.jar;C:\Users\X\.m2\repository\org\springframework\spring-core\5.0.7.RELEASE\spring-core-5.0.7.RELEASE.jar;C:\Users\X\.m2\repository\org\springframework\spring-jcl\5.0.7.RELEASE\spring-jcl-5.0.7.RELEASE.jar;C:\Users\X\.m2\repository\org\springframework\spring-expression\5.0.7.RELEASE\spring-expression-5.0.7.RELEASE.jar;C:\Users\X\.m2\repository\org\springframework\spring-context-support\5.0.7.RELEASE\spring-context-support-5.0.7.RELEASE.jar;C:\Users\X\.m2\repository\org\springframework\spring-beans\5.0.7.RELEASE\spring-beans-5.0.7.RELEASE.jar;C:\Users\X\.m2\repository\org\springframework\spring-jdbc\5.0.7.RELEASE\spring-jdbc-5.0.7.RELEASE.jar;C:\Users\X\.m2\repository\org\springframework\spring-tx\5.0.7.RELEASE\spring-tx-5.0.7.RELEASE.jar;C:\Users\X\.m2\repository\org\slf4j\jcl-over-slf4j\1.7.25\jcl-over-slf4j-1.7.25.jar;C:\Users\X\.m2\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;C:\Users\X\.m2\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;C:\Users\X\.m2\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;C:\Users\X\.m2\repository\org\mybatis\mybatis\3.4.5\mybatis-3.4.5.jar;C:\Users\X\.m2\repository\org\mybatis\mybatis-spring\1.3.1\mybatis-spring-1.3.1.jar;C:\Users\X\.m2\repository\commons-dbcp\commons-dbcp\1.4\commons-dbcp-1.4.jar;C:\Users\X\.m2\repository\commons-pool\commons-pool\1.6\commons-pool-1.6.jar;C:\Users\X\.m2\repository\mysql\mysql-connector-java\5.1.46\mysql-connector-java-5.1.46.jar;C:\Users\X\.m2\repository\org\freemarker\freemarker\2.3.28\freemarker-2.3.28.jar;C:\Users\X\.m2\repository\org\projectlombok\lombok\1.16.22\lombok-1.16.22.jar;C:\Users\X\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.9.6\jackson-databind-2.9.6.jar;C:\Users\X\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;C:\Users\X\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.9.6\jackson-core-2.9.6.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2018.2.4\lib\idea_rt.jar;C:\Users\X\.IntelliJIdea2018.2\system\captureAgent\debugger-agent.jar


            File curDir = new File(this.getClass().getClassLoader().getResource(".").getPath());
            File outDir = new File(curDir.getParent() + "/output");
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------------");
        return path;
    }

}
