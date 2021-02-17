package com.arc.test.statics;

/**
 * @author may
 * @since 2019/7/24 12:17
 */
public class TestStatic {

    public static void main(String[] args) {
        fun1();
    }

    private static void fun1() {
        new TestStatic().getData();
    }

    private   void getData() {
        System.out.println("是否可以从一个静态（static）方法内部发出对非静态（non-static）方法的调用？\n" +
                "不可以，静态方法只能访问静态成员，因为非静态方法的调用要先创建对象，在调用静态方法时可能对象并没有被初始化。");
        System.out.println("毫无疑问是不能直接访问的，但是间接访问时可以的，先实例化对象");
    }
}




