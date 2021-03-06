package com.arc.code.generator.controller.data;

import com.alibaba.fastjson.JSON;
import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.service.FreemarkerGeneratorService;
import com.arc.code.generator.utils.ZipFileUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 代码生成的API
 *
 * @author 叶超
 * @since 2019/10/20 21:19
 */
@Api(tags = "代码生成的API")
@RestController
@RequestMapping("/v1/process")
public class GenerateCodeV1Controller {

    private final static Logger log = LoggerFactory.getLogger(GenerateCodeV1Controller.class);

    @Autowired
    private FreemarkerGeneratorService freemarkerGeneratorService;

    public static void openOutputDir(String outPath) {
        try {
            Desktop.getDesktop().open(new File(outPath));
        } catch (IOException exception) {
            log.error("error", exception);
        }
    }

    /**
     * success
     * 表单提交可以用
     * -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
     * at RequestParam Map<String, Object> parameterMap
     *
     * @param parameterMap Map
     */
    @PostMapping(value = "/form")
    public void testMap2ByForm(@RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {

        parameterMap.put("mapperNamespace", "com.test");
        parameterMap.put("serviceNamespace", "com.test");
        //1、创建文件

        com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext codeGeneratorContext = JSON.parseObject(JSON.toJSONString(parameterMap), com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext.class);
        ArcCodeGeneratorContext propertiesProvider = freemarkerGeneratorService.processByContext(codeGeneratorContext);

        //2、记录结果
        boolean success = propertiesProvider.isSuccess();

        String output = propertiesProvider.getOutput();

        log.info("输出目录={}    \n生成结果={}", output, (success ? "成功" : "失败"));

        // 2.5 打开文件夹
//        openOutputDir(output);
//        Runtime.getRuntime().exec(output);

        //3、文件下载
        ZipFileUtil.downloadFilesZip(response, output);
    }

    /**
     * 测试生成逻辑
     *
     * @return ResponseEntity
     */
    @PostMapping(value = "/jdbc")
    public ResponseEntity fixed(@RequestBody com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext generatorContext) {
        try {
            // 主逻辑
            ArcCodeGeneratorContext result = freemarkerGeneratorService.processByContext(generatorContext);
            log.debug("result={}", JSON.toJSONString(result));
        } catch (Exception exception) {
            String message = "" + exception.getCause() + exception.getCause();
            log.error("error", exception);
            MultiValueMap<String, String> map = new HttpHeaders();
            ResponseEntity responseEntity = new ResponseEntity(message, map, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
        return ResponseEntity.ok("success");
    }

}

//    /**
//     * 参数由调用者传入后生成相关数据库表的一套 model、mapper、service、controller
//     *
//     * @param schemaName
//     * @param tableName
//     * @param tableAlias
//     * @param rootNamespace
//     * @param output
//     * @return
//     */
//    @Deprecated
//    @GetMapping(value = "/1")
//    public ResponseEntity test2(
//            @RequestParam(required = true) String schemaName,
//            @RequestParam(required = true) String tableName,
//            @RequestParam(required = true) String tableAlias,
//            @RequestParam(required = true) String rootNamespace,
//            @RequestParam(required = true) String output
//    ) {
//        Map<String, Object> result = new HashMap<>();
//        result.put("schemaName", schemaName);
//        result.put("tableName", schemaName);
//        return ResponseEntity.ok(result);
//    }


//class ProcessResult {
//
//    private String filename;
//
//    private boolean success;
//
//}
//

