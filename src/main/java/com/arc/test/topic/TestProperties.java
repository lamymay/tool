package com.arc.test.topic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 叶超
 * @since 2019/8/25 20:05
 */
public class TestProperties {

    public static void main(String[] args) throws IOException {

//        在开发过程中，我们经常会遇到读取配置文件的情况，对于配置文件的读取，根据环境等情况又各有不同，一般情况下，如果从非jar包中使用相对/路径，比较简单，就不在累述了，而在很多
//        情况下，我们需要把我们的class打包成jar文件，进行使用，这时就会发现，我们先前如果没有考虑到这些，可能就行不通了，那么，该如何解决呢？方法如下
//        有如下路径 ：
//        Web-info--|-->classes--->conf-->config.properties
//                |-->lib
//        此时加入我们需要读取config.properties，在不使用jar包时，使用如下方式读取，不失为一种方法：
//        File f = new File(this.getClass().getResource("/").getPath());
//        f = new File(f.getPath() + "/conf/config.properties");
//        注：f.getPath()即为当class所在的绝对路径。如：c:\javasrc\web-inf\classes
//        然后,对文件对象进行处理，就能把配置信息读取出来了，但是加入如上class被打包成jar文件，那么，在程序执行到这里时，就会无法找到配置文件，那么该如何处理呢？
//        处理方法如下：

        String s_config = "conf/config-test.properties";
        InputStream in = ClassLoader.getSystemResourceAsStream(s_config);
        if (in == null) {
            System.out.println(" 打开 " + s_config + "失败！");
        } else {
            Properties properties = new Properties();
            properties.load(in);
            System.out.println(new String( properties.getProperty("a.b.c").getBytes(),"UTF-8"));
            System.out.println( properties.getProperty("a"));
//
//接下来就可以通过 properties.getProperty(String obj) 方法对进行配置信息读取了
        }


    }
}
