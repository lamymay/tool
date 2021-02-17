package com.arc.code.generator.controller.data;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.config.properties.value.TestValue;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试读取配置文件
 *
 * @author 叶超
 * @since 2019/10/3 15:29
 */
@Api(tags = "测试读取配置文件")
@Slf4j
@RestController
@RequestMapping("/test")
public class PropertyTestController {


    /**
     * 配置属性是否可以正常注入 1
     */
    @Autowired
    @Qualifier("arcPropertiesProviderImpl1")
    private ArcPropertiesProvider arcPropertiesProvider;

    /**
     * 配置属性是否可以正常注入 2
     */
    @Resource
    private TestValue testValue;

    /**
     * 测试返回ResponseEntity
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/1")
    public ResponseEntity<Map<String, Object>> testResponseEntity() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "arc");
        map.put("age", 12);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(200);
        return bodyBuilder.body(map);
    }


    /**
     * 测试从配置文件中读取配置
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/2/value")
    public ResponseEntity getValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("generatorProperties", arcPropertiesProvider);
        map.put("testValue", testValue);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(500);
        return bodyBuilder.body(map);
    }


}
