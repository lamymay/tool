package com.arc.code.generator.controller.data;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.model.domain.TableMeta;
import com.arc.code.generator.service.MetaService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author may
 * @since 2021/4/27 14:26
 */
@Api(tags = "代码生成的API")
@Slf4j
@RestController
@RequestMapping("/test/meta")
public class TableMetaController {

    /**
     * 测试基础数据是否正常获取
     */
    @Resource
    private MetaService metaService;

    //================== 测试  ==================


    //测试jdbc访问数据库
    @GetMapping(value = "/jdbc")
    public ResponseEntity selectTableMateByJDBC(@RequestBody ArcPropertiesProvider propertiesProvider) {

        TableMeta tableMeta = metaService.selectTableMateByJDBC(propertiesProvider);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(200);
        ResponseEntity<TableMeta> responseEntity = bodyBuilder.body(tableMeta);
        log.debug("ResponseEntity={}", responseEntity);
        return responseEntity;
    }

}
