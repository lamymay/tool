package com.arc.code.generator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

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

}
