package com.arc.code.generator.config.swagger2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * swagger2的配置作为注解引入到您需要的项目中去
 * 注意需要在您的使用swagger2的项目中yml中增加配置
 *
 * @author yechao
 * @since 2019/12/7 17:38
 */
@Import(Swagger2ApiConfiguration.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableSwagger2ForArcApi {
}
