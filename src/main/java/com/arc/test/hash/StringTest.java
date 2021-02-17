package com.arc.rabbitmq3.controller.test;

public  class StringTest {
    public static void main(String args[]) {
        //测试一：同地址，相等
        User u1 = new User();
        User u2 = u1;
        u1.name = "sss";
        boolean b = u1.equals(u2);
        System.out.println(b);

        //测试二：地址不同，类型、内容相同，相等
        User u3 = new User();
        User u4 = new User();
        u3.name = "sss";
        u4.name = "sss";
        boolean b2 = u3.equals(u4);
        System.out.println(b2);

        //String作为一个对象来使用
        //
        //例子一：对象不同，内容相同，"=="返回false，equals返回true
        String s1 = new String("java");
        String s2 = new String("java");

        System.out.println(s1==s2);            //false
        System.out.println(s1.equals(s2));    //true


        //String作为一个基本类型来使用
        //
        //如果值不相同，对象就不相同，所以"==" 和equals结果一样
        String s3 = "java";
        String s4 = "java";

        System.out.println(s3==s4);            //true
        System.out.println(s3.equals(s4));    //true
        //如果String缓冲池内不存在与其指定值相同的String对象，那么此时虚拟机将为此创建新的String对象，并存放在String缓冲池内。
        //
        //如果String缓冲池内存在与其指定值相同的String对象，那么此时虚拟机将不为此创建新的String对象，而直接返回已存在的String对象的引用。
    }
}
