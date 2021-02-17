package com.arc.test.topic;

/**
 * @author 叶超
 * @since 2019/8/25 18:39
 */
public class TestInterface2 {

    //方法中不能由final修饰

    final void a() {
    }

    //编译报错 void    final b() {    }

    static void c() {
    }

    static final void d() {
    }

    //final abstract void  e() {    }


}
