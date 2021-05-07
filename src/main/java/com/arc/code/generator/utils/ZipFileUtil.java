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
     * @param rootFile 指定目录
     * @return 返回指定目录下的全部文件
     */
    public static List<File> listNextFile(File rootFile) {
        File[] listOfFiles = rootFile.listFiles();
        List<File> fileList = new LinkedList<>();
        if (listOfFiles == null || listOfFiles.length < 1) {
            return fileList;
        }
        for (File file : listOfFiles) {
            if (file.isFile()
                    && !".gitignore".equals(file.getName())
                    && !"application.yml".equals(file.getName())
                    && !"pom.xml".equals(file.getName())
                    && !"application-dev.yml".equals(file.getName())
            ) {
                fileList.add(file);
            }
            if (file.isDirectory()) {
                fileList.addAll(listNextFile(file));
            }
        }
        return fileList;
    }

    public File createDefaultFile(String outputParameter) {
        if (outputParameter == null || outputParameter.trim().length() == 0 || "".equals(outputParameter.trim())) {
            outputParameter = "/output";
        }

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
    public static void downloadFilesZip(HttpServletResponse response, @NotNull String output) {
        // 注意:需要关闭的流 有哪些?
        response.setCharacterEncoding("UTF-8");
        //获得要下载的文件名
        String fileRootPath = output;
        String zipName = System.currentTimeMillis() + ".zip";
        String zipPath = fileRootPath + zipName;

        //1、获取文件列表 List<File>
        List<File> fileList = ZipFileUtil.listNextFile(new File(fileRootPath));

        if (fileList == null || fileList.size() == 0) {
            throw new RuntimeException("FILE_NOT_EXIST_ERROR");
        }

        //压缩文件的流  *1
        FileOutputStream zipFileOutputStream = null;
        ZipOutputStream zipOutput = null;
        FileInputStream in = null;
        OutputStream out = null;
        FileInputStream fileInputStream;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bis = null;

        try {
            zipFileOutputStream = new FileOutputStream(zipPath);
            bufferedOutputStream = new BufferedOutputStream(zipFileOutputStream);


            zipOutput = new ZipOutputStream(bufferedOutputStream);
            byte[] buffer = new byte[1024];
            int read;

            for (File file : fileList) {
                //读入的文件流 *2
                fileInputStream = new FileInputStream(file);

                bis = new BufferedInputStream(fileInputStream);

                //压缩
                zipOutput.putNextEntry(new ZipEntry(file.getName()));
                while ((read = bis.read(buffer)) != -1) {
                    zipOutput.write(buffer, 0, read);
                }
                // 每一个文件的流需要单独关闭
                fileInputStream.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (zipOutput != null) {
                zipOutput.close();
            }
            //创建输出流，下载zip
            out = response.getOutputStream();
            in = new FileInputStream(zipPath);
            //设置响应头，控制浏览器下载该文件
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(zipName, "UTF-8"));
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        } catch (Exception e1) {
            log.error("异常", e1);
            throw new RuntimeException("文件操作出异常");
        } finally {
            //释放资源 、删除压缩包
            cleanResource(zipFileOutputStream);
            cleanResource(zipOutput);
            cleanResource(in);
            cleanResource(out);
            // 在for循环中已经做了关闭 则此处不用关闭 cleanResource(fileInputStream);
            cleanResource(bufferedOutputStream);
            cleanResource(bis);

            File zipFile = new File(zipPath);
            boolean delete = zipFile.delete();
            String success = delete ? "成功" : "失败";
            log.warn("清理临时文件异常，zip下载路径：" + zipPath + "删除文件" + success);
        }
    }

    public static void outputFilesToZip(@NotNull String output) {
        //获得要下载的文件名
        String zipName = System.currentTimeMillis() + ".zip";
        String zipPath = output + zipName;

        //1、获取文件列表 List<File>
        List<File> fileList = ZipFileUtil.listNextFile(new File(output));

        if (fileList == null || fileList.size() == 0) {
            throw new RuntimeException("FILE_NOT_EXIST_ERROR");
        }

        //压缩文件的流  *1
        FileOutputStream zipFileOutputStream = null;
        ZipOutputStream zipOutput = null;
        FileInputStream in = null;
        FileInputStream fileInputStream;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bis = null;

        try {
            zipFileOutputStream = new FileOutputStream(zipPath);
            bufferedOutputStream = new BufferedOutputStream(zipFileOutputStream);


            zipOutput = new ZipOutputStream(bufferedOutputStream);
            byte[] buffer = new byte[1024];
            int read;

            for (File file : fileList) {
                //读入的文件流 *2
                fileInputStream = new FileInputStream(file);

                bis = new BufferedInputStream(fileInputStream);

                //压缩
                zipOutput.putNextEntry(new ZipEntry(file.getName()));
                while ((read = bis.read(buffer)) != -1) {
                    zipOutput.write(buffer, 0, read);
                }
                // 每一个文件的流需要单独关闭
                fileInputStream.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (zipOutput != null) {
                zipOutput.close();
            }
            //创建输出流，下载zip
        } catch (Exception e1) {
            log.error("异常", e1);
            throw new RuntimeException("文件操作出异常");
        } finally {
            //释放资源 、删除压缩包
            cleanResource(zipFileOutputStream);
            cleanResource(zipOutput);
            cleanResource(in);
            // 在for循环中已经做了关闭 则此处不用关闭 cleanResource(fileInputStream);
            cleanResource(bufferedOutputStream);
            cleanResource(bis);

            log.warn("清理临时文件异常，zip下载路径：" + zipPath);
        }
    }


    public static void cleanResource(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("cleanResource inputStream 异常", e);
            }
        }
    }

    public static void cleanResource(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error("cleanResource outputStream异常", e);
            }
        }
    }


}
