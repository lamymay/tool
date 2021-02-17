package com.arc.code.generator.utils;

import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author 叶超
 * @since 2019/10/2 17:26
 */
public class NameUtil {

    /**
     * 将首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCaseFirstWord(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 将首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCaseFirstWord(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }


    /**
     * 如 sys_name 变成 SysName
     *
     * @param str
     * @return
     */
    public static String replaceUnderLineAndUpperCase(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        String result = sb.toString().replaceAll("_", "");
        return StringUtils.capitalize(result);
    }


    //for test
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(replaceUnderLineAndUpperCase("abc"));
        System.out.println(replaceUnderLineAndUpperCase("_abc"));
        System.out.println(replaceUnderLineAndUpperCase("__abc"));
        System.out.println("--------------");
        System.out.println(replaceUnderLineAndUpperCase("ABC"));
        System.out.println(replaceUnderLineAndUpperCase("t_abc"));
        System.out.println(replaceUnderLineAndUpperCase("sys_abc"));
        System.out.println(replaceUnderLineAndUpperCase("sys_abc"));

//        createFile();
    }


    private static void createFile() throws FileNotFoundException {

        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        System.out.println(path);///C:/Users/X/Desktop/Zan/git/arc/server-db/jpa-tool/target/classes/

//        ApplicationHome home = new ApplicationHome(getClass());
//        File jarFile = home.getSource();

        File file = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println(file.getPath());

        System.out.println(ResourceUtils.getURL("/").getPath());

//            if (targetFile.exists()) {
//                boolean mkdir = targetFile.mkdir();
//                log.debug("targetFile 缺失  {}",mkdir);
//            }

        File file2 = ResourceUtils.getFile("classpath:templates/test.ftl");

        System.out.println(file2.getName());
        if (file2.exists()) {
            File[] files = file2.listFiles();
            if (files != null) {
                for (File childFile : files) {
                    System.out.println(childFile.getName());//model.ftl
                }
            }
        }
    }

}
