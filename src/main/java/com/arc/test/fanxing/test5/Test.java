package com.arc.test.fanxing.test5;


import com.arc.test.fanxing.test1.Model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 内部错误码
 * 规定一定范围是一类错误，如：
 * 0001-0999 服务中的一些系统级别的异常【重大错误】
 * 1000-1999 业务中的非特定错误【运行中的普通错误】
 * 2000-2999 数据库操作级别的异常【db错误】
 * 3000-3999
 * 4000-4999 流程相关异常
 * ...
 * 9000-9998
 * <p>
 * <p>
 * ：）
 * 1）注意：在这里错误码约束有效， 下面的已有code定义看着删吧！！！
 * 2）注意： ####  看标题 ！！！
 *
 * @author 叶超
 * @since 2018/04/10
 */
public class Test<T> {

    private Model model;

    /**
     * 创建一个Class的对象来获取泛型的class
     */
    private Class<T> clz;

    public Test(Model model) {
        this.model = model;
    }

    public static <T> void main(String[] args) {

//        Test test = new Test(new Model(1L, "123"));
//        Class clz = test.getClz();
//        System.out.println(clz);

        Foo<String> foo = new Foo<String>() {
        };
        // 在类的外部这样获取
        Type type = ((ParameterizedType) foo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(type);
        // 在类的内部这样获取
        System.out.println(foo.getTClass());


    }

    @SuppressWarnings("unchecked")
    public Class<T> getClz() {
        if (clz == null) {
            clz = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        }
        return clz;
    }

}

abstract class Foo<T> {
    public Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}

