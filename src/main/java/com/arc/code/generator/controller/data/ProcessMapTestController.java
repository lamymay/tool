package com.arc.code.generator.controller.data;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 测试Controller接收map
 *
 * @author 叶超
 * @since 2019/10/3 15:29
 */
@Api(tags = "测试读取配置文件")
@Slf4j
@RestController
@RequestMapping("/test/process")
public class ProcessMapTestController {


    /**
     * 测试controller接收map数据
     * -H 'content-type: application/json' \
     * at RequestBody Map<String, Object> parameterMap
     *
     * @param parameterMap
     * @return
     */
    @PostMapping(value = "/map/1")
    public ResponseEntity testMap1(@RequestBody Map<String, Object> parameterMap) {
        //return ResponseEntity.ok(freemarkerGeneratorService.executeByMap(parameterMap));
        return ResponseEntity.ok(parameterMap);
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
    @PostMapping(value = "/map/form")
    public ResponseEntity testMap2ByForm(@RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        return ResponseEntity.ok(parameterMap);
    }

    /**
     * 错误 将无法收到参数
     *
     * @param parameterMap
     * @return
     */
    @PostMapping(value = "/map/3")
    public ResponseEntity testMap3(Map<String, Object> parameterMap) {
        return ResponseEntity.ok(parameterMap);
    }

}
