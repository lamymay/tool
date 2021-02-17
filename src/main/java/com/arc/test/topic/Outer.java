package com.arc.test.topic;

/**
 * @author 叶超
 * @since 2019/8/25 18:27
 */
public class Outer {
    public void a() {
        Innor innor = new Innor();

    }

    public class Innor {
    }

    public static void main(String[] args) {
        TestInterface.g(1);
        Outer outer = new Outer();
        //编译报错 Innor innor = new Innor();
        //编译报错 outer.Innor innor = new outer.Innor();
         //编译报错 Outer.Innor innor = new Outer.Innor();


    }
}
