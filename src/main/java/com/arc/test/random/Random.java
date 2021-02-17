package com.arc.test.random;

/**
 * @author 叶超
 * @since: 2019/7/6 8:34
 */
public class Random {
    public static void main(String[] args) {

        //生成6位随机数字
        System.out.println((int)((Math.random()*9+1)*100000));
        //生成5位随机数字
        System.out.println((int)((Math.random()*9+1)*10000));
        //生成4位随机数字
        System.out.println((int)((Math.random()*9+1)*1000));
        //生成3位随机数字
        System.out.println((int)((Math.random()*9+1)*100));
        //生成2位随机数字
        System.out.println((int)((Math.random()*9+1)*10));
        //生成1位随机数字
        System.out.println((int)((Math.random()*9+1)));
    }
}
