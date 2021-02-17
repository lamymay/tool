package com.arc.test.fanxing.test2;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author may
 * @since 2019/7/16 22:46
 */
//将该参数化类作为成员的类 的成员来获取
//对于继承父类而来的对象，使用getGenericSuperclass()与getActualTypeArguments()
public class Test2 extends ArrayList<ItemVo> {

    private static final long serialVersionUID = -1375958143091889386L;

    public static void main(String args[]) throws Exception {
        //获取Test类的父类ArrayList<ItemVo>的Type
        Type t = Test2.class.getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        Type[] ts = pt.getActualTypeArguments();//这样就获取了ArrayList<ItemVo>中的泛型
        for (int i = 0; i < ts.length; ++i) {
            System.out.println(i + " 父类中的泛型为：" + ts[i]);
            Class<?> c = (Class<?>) ts[i];//如果需要使用这个类型 进行强转即可
            System.out.println(i + " 强转后类型为：" + c);
        }
    }
}
