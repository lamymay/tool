package com.arc.code.generator.controller.data;

import com.arc.code.generator.mapper.MetaMapper;
import com.arc.code.generator.model.domain.meta.TableMeta;
import com.arc.code.generator.service.FreemarkerGeneratorService;
import com.arc.code.generator.utils.ZipFileUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 代码生成的API
 *
 * @author 叶超
 * @since 2019/10/20 21:19
 */
@Api(tags = "代码生成的API")
@Slf4j
@RestController
@RequestMapping("/v1/process")
public class GenerateCodeController {

    @Autowired
    private FreemarkerGeneratorService freemarkerGeneratorService;

    /**
     * 测试基础数据是否正常获取
     */
    @Resource
    private MetaMapper metaMapper;


    /**
     * 文件下载3-- 遍历一个文件夹
     * POST http://127.0.0.1:8001/zero/test/zip/download/v3   wait response zip data
     *
     * @param parameterMap
     * @param response
     * @return
     */
    @RequestMapping("/download/v3")
    public ResponseEntity testV3(@RequestBody Map<String, Object> parameterMap, HttpServletResponse response) {

        //1、创建文件
        Map<String, Object> resultMap = freemarkerGeneratorService.executeByMap(parameterMap);

        // 结果输出
        Boolean result = (Boolean) resultMap.get("result");
        log.info("\n代码生成输出目录为 {}    \n生成结果：{}", resultMap.get("output"), (result ? "成功" : "失败"));

        //异常的
        if (!result) {
            return ResponseEntity.ok(resultMap);
        }

        //正常成功的-->文件下载
        ZipFileUtil.downloadFilesZip(response, (String) resultMap.get("output"));
        return null;
    }


    /**
     * success
     * 表单提交可以用
     * -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
     * at RequestParam Map<String, Object> parameterMap
     *
     * @param parameterMap
     * @return
     */
    @PostMapping(value = "/form")
    public void testMap2ByForm(@RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        //1、创建文件
        Map<String, Object> resultMap = freemarkerGeneratorService.executeByMap(parameterMap);

        //2、记录结果
        Boolean result = (Boolean) resultMap.get("result");
        log.info("\n代码生成输出目录为 {}    \n生成结果：{}", resultMap.get("output"), (result ? "成功" : "失败"));

        //3、文件下载
        ZipFileUtil.downloadFilesZip(response, (String) resultMap.get("output"));
    }


    //================== 测试  ==================


    /**
     * 测试jdbc访问数据库
     *
     * @param tableSchema 数据库
     * @param tableName   表名称
     * @return TableMeta
     */
    @GetMapping(value = "/test/mate/{tableSchema}/{tableName}")
    public ResponseEntity meate(@PathVariable String tableSchema, @PathVariable String tableName) {
        TableMeta tableMeta = metaMapper.get(tableSchema, tableName);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(201);
        return bodyBuilder.body(tableMeta);
    }

    /**
     * 测试生成逻辑
     *
     * @return map
     */
    @GetMapping(value = "/test/fixed")
    public ResponseEntity fixed() {
        return ResponseEntity.ok(freemarkerGeneratorService.execute(null));
    }

//    /**
//     * 参数由调用者传入后生成相关数据库表的一套 model、mapper、service、controller
//     *
//     * @param schemaName
//     * @param tableName
//     * @param tableAlias
//     * @param rootNamespace
//     * @param mapperNamespace
//     * @param modelNamespace
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
//            @RequestParam(required = true) String mapperNamespace,
//            @RequestParam(required = true) String modelNamespace,
//            @RequestParam(required = true) String output
//    ) {
//        Map<String, Object> result = new HashMap<>();
//        result.put("schemaName", schemaName);
//        result.put("tableName", schemaName);
//        return ResponseEntity.ok(result);
//    }

}
