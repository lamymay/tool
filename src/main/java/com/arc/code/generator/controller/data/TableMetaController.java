package com.arc.code.generator.controller.data;

import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.model.domain.TableMeta;
import com.arc.code.generator.service.MetaService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author may
 * @since 2021/4/27 14:26
 */
@Api(tags = "代码生成的API")
@RestController
@RequestMapping("/test/meta")
public class TableMetaController {

    private final Logger log = LoggerFactory.getLogger(TableMetaController.class);

    /**
     * 测试基础数据是否正常获取
     */
    @Resource
    private MetaService metaService;


    //================== 测试  ==================

    //测试jdbc访问数据库
    @GetMapping(value = "/jdbc")
    public ResponseEntity selectTableMateByJDBC(@RequestBody ArcCodeGeneratorContext generatorContext) {

        TableMeta tableMeta = metaService.selectTableMateByJDBC(generatorContext);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(200);
        ResponseEntity<TableMeta> responseEntity = bodyBuilder.body(tableMeta);
        log.debug("ResponseEntity={}", responseEntity);
        return responseEntity;
    }

}
