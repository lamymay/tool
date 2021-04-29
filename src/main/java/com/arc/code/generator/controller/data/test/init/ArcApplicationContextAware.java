package com.arc.code.generator.controller.data.test.init;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author may
 * @since 2021/4/27 17:36
 */
@Component
public class ArcApplicationContextAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ArcApplicationContextAware 执行啦" + System.currentTimeMillis());

    }
}
