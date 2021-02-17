package com.arc.code.generator.controller.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 启动服务使用web页面录入参数后生产代码
 *
 * @author May
 * @since 2020/1/16 12:55
 */
@Slf4j
@Controller
public class GenerateCodeByWebController {

    @GetMapping({"/", "/test", "/code"})
    public String parameterCollectionPage() {
        log.debug("时间={}", System.currentTimeMillis());
        return "/view/collection";
    }
}
