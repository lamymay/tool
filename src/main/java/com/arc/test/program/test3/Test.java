package com.arc.test.program.test3;

public class Test {

    public static void testStatic() {
        System.out.println("类方法--也就是static方法");
        Test t = new Test();
        t.test();
    }


    public void test() {
        System.out.println("实例方法");
    }

    public static void main(String[] args) {
        Test.testStatic();


    }
}
