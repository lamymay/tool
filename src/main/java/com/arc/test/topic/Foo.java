package com.arc.test.topic;

/**
 * @author 叶超
 * @since 2019/8/25 15:57
 */
public class Foo {
    public static void main(String[] args) {

        System.out.println(4&7);//4
        //& 运算，任何二进制数和0进行& 运算，结果为0，和1进行&运算结果为原值，
        //1111
        //0 0000
        //1 0001
        //2 0010
        //3 0011
        //4 0100
        //5 0101
        //6 0110
        //7 0111


        float l = 3.9f;
        System.out.println(l);
        long temp = (int) 3.9;
        System.out.println(temp%2);

        try {
            return;
        } finally {
            System.out.println( "Finally" );
        }
    }
}

//输出结果是什么?   A
//A. Finally
//B.编译失败
//C. 代码正常运行但没有任何输出.
//D. 运行时抛出异常
