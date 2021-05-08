package com.arc.code.generator.utils;

import com.alibaba.fastjson.JSON;

/**
 * @author may
 * @since 2021/5/8 9:31
 */
public abstract class JsonUtil {

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }
}
