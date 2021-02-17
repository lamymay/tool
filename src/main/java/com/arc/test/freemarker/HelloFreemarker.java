package com.arc.test.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author 叶超
 * @since 2019/10/4 14:43
 */
@Slf4j
public class HelloFreemarker {

    /**
     * step1 创建freeMarker配置实例
     * step2 获取模版路径
     * step3 创建数据模型
     * step4 加载模版文件
     *
     * @param args
     * @throws IOException
     * @throws TemplateException
     */
    public static void main(String[] args) throws IOException, TemplateException {



        //https://freemarker.apache.org/docs/pgui_quickstart_merge.html
        //首先，您必须创建一个 freemarker.template.Configuration实例并调整其设置。一个Configuration实例是存储FreeMarker的应用水平设置的中心位置。
        // 此外，它还处理预解析模板（即 对象）的创建和 缓存Template。
        //
        //通常，您只会在应用程序（可能是servlet）生命周期开始时执行一次此操作：
        //
        //创建您的Configuration实例，并指定FreeMarker版本，（此处为2.3.29）是否要应用不是100％的修复程序向下兼容。有关详细信息，请参见Configuration JavaDoc。
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

        //指定模板文件的来源。在这里我设置一个
        //它的纯目录，但也可以使用非文件系统源：

        cfg.setDirectoryForTemplateLoading(new File("T:\\Project\\Za\\tool\\src\\main\\resources\\templates\\version-1"));

        //从这里我们将为新项目设置推荐的设置。这些不是向后兼容性的默认值。
        //设置模版首选字符集（会存储）。UTF-8为在大多数应用中是一个不错的选择：
        cfg.setDefaultEncoding("UTF-8");

        //设置错误的显示方式。
        //在网页开发过程中* TemplateExceptionHandler.HTML_DEBUG_HANDLER 更好。
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //不要在FreeMarker内记录异常，否则它将引发您的异常：
        cfg.setLogTemplateExceptions(false);

        //将在模板处理过程中抛出的未经检查的异常包装到TemplateException -s中：
        cfg.setWrapUncheckedExceptions(true);

        cfg.setTimeZone(TimeZone.getDefault());
        cfg.setTimeFormat("HH:mm:ss");
        cfg.setDateFormat("yyyy-MM-dd");
        cfg.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        //从现在开始，您应该使用此单个 配置实例（即，其单例）。但是请注意，如果系统有多个使用FreeMarker的独立组件，那么它们当然将使用自己的私有 Configuration实例。
        //警告！
        //不要不必要地重新创建Configuration 实例；这很昂贵，尤其是因为您丢失了模板缓存。Configuration实例意味着是应用程序级别的单例。

        //在多线程应用程序（如网站）中Configuration，此后不得再修改实例中的设置。因此，它可以被视为“有效不变”的对象，因此您可以继续使用安全的发布技术（请参阅JSR 133和相关文献）以使该实例可用于其他线程。例如，通过最终文件或volatile文件或通过线程安全的IoC容器（如Spring提供的容器）发布实例。 Configuration不涉及修改设置的方法是线程安全的。


        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name", "com.arc.model;");
        dataMap.put("age", "DatasourceInfo");
        dataMap.put("date", new Date());

        System.out.println("------测试-----");
        File file = ResourceUtils.getFile("classpath:templates");
        System.out.println(file.getPath());
        System.out.println(file.getPath());
        System.out.println(file.getPath());
        System.out.println(TimeZone.getDefault());
        System.out.println(TimeZone.getDefault());
        System.out.println(TimeZone.getDefault());

        //获取模板
        //模板由freemarker.template.Template实例表示 。通常，您可以使用Template实例从Configuration实例获取实例 。 getTemplate方法。如果将示例模板存储在较早的 set目录的 test.ftlh文件中，则可以执行以下操作：
        //
        //模板temp = cfg.getTemplate（“ test.ftlh”）;
        //这为您提供Template了通过读取/where/you/store/templates/test.ftlh 和解析而创建的实例 。该Template实例存储在解析形式的模板，而不是文字。如果模板丢失或语法错误，getTemplate则会抛出异常。
        //
        //Configuration缓存 Template实例，因此当您cfg.getTemplate("test.ftlh")下次调用 时，它可能不会再次读取和解析模板文件，只是返回与Template第一次相同的 实例。
        Template template = cfg.getTemplate("HelloFreemarker-1.ftl");

        // step5 生成数据
        File targetFile = new File("I:" + File.separator + "HelloFreemarker1.txt");

        //Writer out = new FileWriter( targetFile);

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));
        // step6 输出文件
        template.process(dataMap, out);
        out.flush();
        out.close();
        log.info("：)  文件创建成功 !");

    }


    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date stringToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
