package com.arc.test.fanxing.test2;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author may
 * @since 2019/7/16 22:48
 */
//对于实现接口而来的对象，使用getGenericInterfaces()与getActualTypeArguments()
public class Test1 {

    private List<ItemVo> itemlist;//我们要获取的泛型类型为ItemVo

    private Map<Integer, ItemVo> itemMap;//我们要获取的泛型类型为Integer和ItemVo

    public Test1() {
        itemlist = new ArrayList<>();
        itemMap = new HashMap<>();
    }

    //ParameterizedType
    public static void main(String args[]) throws Exception {

        //将该参数化类作为成员的类 的成员来获取
        //获取成员itemlist
        Field field = Test1.class.getDeclaredField("itemlist");
        System.out.println(field);

        Type t = field.getGenericType();
        ParameterizedType pt = (ParameterizedType) t;
        Type[] ts = pt.getActualTypeArguments();//这样就获取了List<ItemVo>中的泛型


        for (int i = 0; i < ts.length; ++i) {
            System.out.println(i + " itemlist中的泛型为：" + ts[i]);
            Class<?> c = (Class<?>) ts[i];//如果需要使用这个类型 进行强转即可
            System.out.println(i + " 强转后类型为：" + c);
        }
        System.out.println("*************************************************************");
        //获取成员itemMap
        field = Test1.class.getDeclaredField("itemMap");
        System.out.println(field);

        t = field.getGenericType();
        pt = (ParameterizedType) t;
        ts = pt.getActualTypeArguments();//这样就获取了Map<Integer, ItemVo>中的泛型
        for (int i = 0; i < ts.length; ++i) {
            System.out.println(i + " itemMap中的泛型为：" + ts[i]);
            Class<?> c = (Class<?>) ts[i];//如果需要使用这个类型 进行强转即可
            System.out.println(i + " 强转后类型为：" + c);
        }

    }
}
