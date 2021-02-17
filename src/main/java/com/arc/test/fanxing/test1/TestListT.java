package com.arc.test.fanxing.test1;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author may
 * @since 2019/7/16 22:19
 */
public class TestListT {

    public static void main(String[] args) {
        ArrayList<Model> models = new ArrayList<>();
        models.add(new Model(1L,"ABC"));
        models.add(new Model(2L,"B"));
//        Class aClass = fun1(models);
//        System.out.println(aClass);
        fun2(models);

        //returnTypeClass就是返回类型了
    }

    private static <T> void fun2(ArrayList<T> models) {
        Class<? extends ArrayList> clz = models.getClass();

        Class<?> superclass = clz.getSuperclass();
        System.out.println(superclass);

        AnnotatedType annotatedSuperclass = clz.getAnnotatedSuperclass();
        System.out.println(annotatedSuperclass);

        Class<?> componentType = clz.getComponentType();
        System.out.println(componentType);
        System.out.println();
        System.out.println(clz.getPackage());
        System.out.println(clz.getPackage());
        System.out.println(clz.getTypeParameters());
        System.out.println(clz.getClassLoader());
        System.out.println(clz.getTypeName());
    }

    private static <T> Class fun1(List<T> models) {
        List<T> list = new ArrayList<>(0);
        list.addAll(models);
        Method method = null;
        try {

            Class<? extends List> clz = list.getClass();
//            clz.getAnnotation()
            method = list.getClass().getMethod("get", null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Class returnTypeClass = method.getReturnType();
        System.out.println(returnTypeClass);
        return returnTypeClass;
    }

}

//已知类型Class1,获取它的属性集合，你上面的代码已经有了。
//有点要注意，在获取第一个泛型的形参类型那里，不用的类型继承的泛型接口也不同，例如，可能是IList、IList、Dictionary的，所以不一定是第一个泛型就可以获取出T。这里应该还是有根据实际情况做相应处理的过程。
//另外，如果已知是List的话，有一个很简单的方法：
//xx.GetType().GetMethod("Find").ReturnType。就可以返回Class1的Type了，这种就很有针对性，专门针对List的T Find()函数设计。获得Find函数的返回类型即可。
