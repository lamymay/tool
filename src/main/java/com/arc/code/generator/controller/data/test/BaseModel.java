package com.arc.code.generator.controller.data.test;

import java.io.Serializable;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 泛型话父类
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since 2019/10/3 15:29
 */
public class BaseModel implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(this.getClass());
        builder.append("@");
        builder.append(this.hashCode());
        builder.append("[");
        String info = toString(this, this.getClass());
        builder.append(info);
        builder.append("]}");
        return builder.toString();
    }

    /**
     * @param obj
     * @param clazz
     * @return
     */
    private static String toString(Object obj, Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        Class<?> parentClass = clazz.getSuperclass();
        if (parentClass != null) builder.append(toString(obj, parentClass));

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();

            // 跳过静态变量以及常量
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                continue;
            }

            // 跳过内部类，防止栈溢出
            if (name.startsWith("this$")) {
                continue;
            }

            if (field.getDeclaredAnnotation(ToStringIgnore.class) != null) {
                continue;
            }

            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value instanceof String)
                    builder.append(name + "=\"" + value.toString() + "\";");
                else
                    builder.append(name + "=" + value.toString() + ";");
            } catch (Exception e) {
            }
        }
        if (builder.length() > 0) {
            return builder.substring(0, builder.length() - 1);
        }
        return builder.toString();
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface ToStringIgnore {
        // 该注释用于 忽略toString中的字段输出，例如password字段等
    }
}
