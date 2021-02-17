package com.arc.code.generator.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文件工具类
 *
 * @author may
 * @since 2020/1/10 22:21
 */
@Slf4j
public class ZipFileUtil {


    /**
     * 返回指定目录下的全部文件
     *
     * @param rootFileFolder
     * @return
     */
    public static List<File> listNextFile(String rootFileFolder) {
        File folder = new File(rootFileFolder);
        File[] listOfFiles = folder.listFiles();
        List<File> fileList = null;
        if (listOfFiles != null && listOfFiles.length > 0) {
            fileList = new LinkedList<>();
        }
        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    public File createDefaultFile(String outputParameter) {
        if (outputParameter == null || outputParameter.trim().length() == 0 || "".equals(outputParameter.trim())) {
            outputParameter = "/output";
        }

        System.out.println(outputParameter);
        System.out.println(outputParameter);


        log.debug("参数 outputParameter={}", outputParameter);
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(".");
        String path = resource.getPath();

        System.out.println(classLoader);
        System.out.println(resource);
        System.out.println(path);

        File currentClassPathDir = new File(path);

        File outDir = new File(currentClassPathDir.getParent() + outputParameter);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        return outDir;
    }

    /**
     * 下载文件压缩包
     *
     * @param response
     * @param output
     * @throws IOException
     */
    //     todo 生成的文件放入临时文件夹，改造使用临时文件 RandomAccessFile
    public static void downloadFilesZip(HttpServletResponse response, @NotNull String output) {

        // 需要 关闭的流
        //FileOutputStream
        //FileOutputStream
        response.setCharacterEncoding("UTF-8");
        //获得要下载的文件名
        String fileRootPath = output;
        String zipName = System.currentTimeMillis() + ".zip";
        String zipPath = fileRootPath + zipName;

        //1、获取文件列表 List<File>
        List<File> fileList = ZipFileUtil.listNextFile(fileRootPath);

        if (fileList == null || fileList.size() == 0) {
            throw new RuntimeException("FILE_NOT_EXIST_ERROR");
        }

        //压缩文件的流  *1
        FileOutputStream zipFileOutputStream = null;
        ZipOutputStream zipOutput = null;
        FileInputStream in = null;
        OutputStream out = null;
        FileInputStream fileInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bis = null;
        try {
            zipFileOutputStream = new FileOutputStream(zipPath);
            bufferedOutputStream = new BufferedOutputStream(zipFileOutputStream);


            zipOutput = new ZipOutputStream(bufferedOutputStream);
            byte[] buffer = new byte[1024];
            int read = 0;

            for (File file : fileList) {
                //读入的文件流 *2
                fileInputStream = new FileInputStream(file);

                bis = new BufferedInputStream(fileInputStream);

                //压缩
                zipOutput.putNextEntry(new ZipEntry(file.getName()));
                while ((read = bis.read(buffer)) != -1) {
                    zipOutput.write(buffer, 0, read);
                }
            }

            bis.close();
            zipOutput.close();
            //创建输出流，下载zip
            out = response.getOutputStream();
            in = new FileInputStream(new File(zipPath));
            //设置响应头，控制浏览器下载该文件
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(zipName, "UTF-8"));
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        } catch (Exception e1) {
            log.error("异常={}", e1);
            throw new RuntimeException("文件操作出异常");
        } finally {

            //释放资源 、删除压缩包

            cleanResource(zipFileOutputStream);
            cleanResource(zipOutput);
            cleanResource(in);
            cleanResource(out);
            cleanResource(fileInputStream);
            cleanResource(bufferedOutputStream);
            cleanResource(bis);

            File zipFile = new File(zipPath);
            boolean delete = zipFile.delete();
            log.warn("清理临时文件异常，zip下载路径：{}，删除文件结果={}", zipPath, delete);
        }
    }

    public static void cleanResource(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("cleanResource inputStream 异常={}", e);
            }
        }
    }

    public static void cleanResource(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error("cleanResource outputStream异常={}", e);
            }
        }
    }


}
