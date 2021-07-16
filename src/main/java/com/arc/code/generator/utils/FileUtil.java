package com.arc.code.generator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author may
 * @since 2021/4/23 8:34
 */
public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 全名？
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File forceCreateFile(String fileName) throws IOException {
        log.debug("文件名称={}", fileName);
        //output + File.separator + fileName
        File newFile = new File(fileName);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        return newFile;
    }

    public static File createOutFile(String outputFileFullName) {
        File outputFile = new File(outputFileFullName);
        if (!outputFile.exists()) {
            //createNewFile这个方法只能在一层目录下创建文件，不能跳级创建，尽管可以用mkdir(s)创建多层不存在的目录，但是不要直接一个File对象搞定目录和文件都需要创建的情况，可以在已有目录下直接用createNewFile创建文件
            if (!outputFile.getParentFile().exists()) {
                boolean mkdirs = outputFile.getParentFile().mkdirs();
                String msg = "输出文件创建过程中,创建父级路径" + (mkdirs ? "成功" : "失败");
                log.info(msg);
                if (!mkdirs) {
                    throw new RuntimeException(msg);
                }
            }
            try {
                boolean result = outputFile.createNewFile();
                log.info("输出文件创建成功={},文件路径={}", result, outputFile.getPath());
            } catch (IOException exception) {
                exception.printStackTrace();
                log.error("输出文件创建异常 createNewFile ", exception);
            }
        }
        return outputFile;
    }


    /*
     * 完成文件的剪切
     */
    public static void main(String[] args) {
//        String source = "C:\\data\\temp\\source";
//        String dest = "C:\\data\\temp\\dest";
        //cutFile(source, dest);
        //deleteSource(new File("C:\\data\\temp"));
        //System.out.println(getTimeString());


        // "/data/test"
        // "C:\\data\\"
        // "C:\\data"
        // "C:/data/"

        System.out.println(concatPath("D:\\test", "demo/", "src\\main\\java", "com.arc.app"));

    }

    private static void cutFile(String source, String dest) {
        //添加开始时间点（计算程序执行时间）
        long time1 = System.currentTimeMillis();

        // 如果目标文件夹不存在，创建新的日期文件夹
        File sourceFile = new File(source);
        File destFile = new File(dest);

        if (!sourceFile.exists()) throw new RuntimeException("源文件不存在");

        if (!destFile.exists()) destFile.mkdirs();

        //将列表中的全部文件剪切到目的文件夹中
        // String dateString = getTimeString();
        File[] files = sourceFile.listFiles();

        if (sourceFile.isDirectory() && files.length == 0) {
            sourceFile.renameTo(destFile);
            //sourceFile.delete();
            return;
        }

        //读取源文件夹的文件名列表
        for (File file : files) {
            if (file.isFile()) {

                String parentPath;
                if (destFile.isDirectory()) {
                    parentPath = destFile.getPath();
                } else {
                    parentPath = destFile.getParent();
                }
                String fileName = file.getName();
                File to = new File(parentPath + File.separator + fileName);
                System.out.println("拷贝文件,源文件地址=" + fileName + "||目标文件地址=" + to.getAbsoluteFile());
                boolean renameTo = file.renameTo(to);
                if (renameTo) file.delete();
            }
            if (file.isDirectory()) {
                String targetPath = dest + File.separator + file.getPath().replace(source, "");
                System.err.println("拷贝文件夹:源路径getAbsolutePath()=" + file.getAbsolutePath() + "||目标路径=" + targetPath);
                cutFile(file.getAbsolutePath(), targetPath);
            }
        }

        deleteSource(new File(source));

        //获得结束时间点
        long time2 = System.currentTimeMillis();
        //System.out.println("消耗的时间为：" + (time2 - time1) + "毫秒");
    }

    private static void deleteSource(File source) {
        if (source.isFile()) {
            log.debug("删除文件:{}", source.getName());
            source.delete();
        }

        if (source.isDirectory()) {
            File[] files = source.listFiles();
            if (files.length == 0) {
                log.debug("删除文件夹:{}", source.getName());
                source.delete();
                return;
            }
            for (File child : files) {
                deleteSource(child);
            }

        }
    }


    @Deprecated
    public static String getPrefix(String output, String projectName, String packageName) {
        return concatPath(output, projectName, packageName);
    }

    public static String concatPath(String... stringList) {
        // String prefix = output + projecctName + File.separator + "src"+ File.separator +"main"+ File.separator +"java"+ File.separator
        if (stringList == null || stringList.length == 0) {
            return "";
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (String str : stringList) {
            if (str != null || str.trim().length() != 0) {
                stringBuffer.append(builtPath(str));
            }
        }
        return stringBuffer.toString();
    }


    private static String builtPath(String str) {
        if (str == null || "".equals(str.trim())) {
            throw new RuntimeException("非法路徑");
        }

        // 如果是win系统
        if (SystemOSUtil.isWindow()) {
            // 把包名称处理下
            if (str.contains(".")) {

                str = str.replace(".", File.separator);


//                String[] split = str.split("\\.");
//                for (String item : split) {
//                    str= builtPath(item);
//                    System.out.println(str);
//                }
            }

            //  斜线开头 清除掉
            if (str.startsWith(File.separator)) str = str.substring(0, 1);

            // 补全斜线结尾
            if (!str.endsWith(File.separator)) str = str + File.separator;

            return str;

        } else {
            // 把包名称处理下
            if (str.contains(".")) {

                str = str.replace(".", File.separator);


//                String[] split = str.split("\\.");
//                for (String item : split) {
//                    str= builtPath(item);
//                    System.out.println(str);
//                }
            }

            if (!str.endsWith(File.separator)) str = str + File.separator;
        }
        return str;

    }

    public static String getTimeString() {
        Date date = new GregorianCalendar().getTime();
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHHmm");
        String dateString = df.format(date);
        log.debug(dateString);
        return dateString;
    }

}
