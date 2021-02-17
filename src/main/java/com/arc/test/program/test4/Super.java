package com.arc.test.program.test4;

/**
 * @author may
 * @since 2019/7/19 14:43
 */

public class Super {
    private static void a() {
        //类方法
    }

    public static void b() {
        //类方法
    }

    public void c() {
        //实例方法
    }

    private void d() {
        //实例方法
    }

}

class Son extends Super {

    //下列哪种说法是正确的（ ）
    //实例方法可直接调用超类的实例方法
    //实例方法可直接调用超类的类方法
    //实例方法可直接调用其他类的实例方法
    //实例方法可直接调用本类的类方法

    public static void main(String args[]) {
        Super s = new Super();
//        Super.a();//B错误，因a为私有的类方法，外类不可访问
        Super.b();//b为公有类方法，外类可用 类名.方法名 访问
        s.c();//c为公有实例方法，外类可用 实例名.方法名 访问
//        s.d();//A错误，因d为私有实例方法，外类不可访问
    }
}

