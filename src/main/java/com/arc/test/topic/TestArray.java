package com.arc.test.topic;


/**
 * @author 叶超
 * @since 2019/8/25 19:00
 */
public class TestArray {


    //Java 数组的三种创建方法
    public static void main(String[] args) {

        //1、声明并赋值
        int[] test1 = {1, 2, 3};

        //2、声明空后赋值
        int[] test2;
        test2 = new int[]{1, 2, 3};

        //3、声明指定元素个数并赋值, 最大能取值 test3[3] ,这样的初始化 数组全为0
        int[] test3 = new int[4];

        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);

        //StringBuilder StringBuffer
        StringBuilder s1 = null;
        StringBuffer s2 = null;
        String   s3 = null;




    }


}
