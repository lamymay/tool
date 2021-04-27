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
    private File forceCreateFile(String fileName) throws IOException {
        log.debug("文件名称={}", fileName);
        //output + File.separator + fileName
        File newFile = new File(fileName);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        return newFile;
    }

}
