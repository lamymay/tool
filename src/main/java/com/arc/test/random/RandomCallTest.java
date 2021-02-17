package com.arc.test.random;

import com.arc.test.random.test3byutil.WeightRandom;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试概率
 *
 * @author yechao
 * @since 2020/6/18 3:06 下午
 */
public class RandomCallTest {

    public static String noticePriority41SubTitle = "今日步数已可换购物红包";
    public static String noticePriority42SubTitle = "[打款提醒] 今日步数已兑成红包";
    public static String noticePriority43SubTitle = "棒！今日步数已达标，可兑红包";
    public static String noticePriority44SubTitle = "今天的无门槛红包可以领啦！";
    public static String noticePriority45SubTitle = "1000步小目标已达成";
    public static String noticePriority46SubTitle = "辛苦攒的步数红包不要了吗？";
    public static String noticePriority47SubTitle = "再不领，今日步数红包就过期";


    private static List<String> temp = new ArrayList<>(16);

    static {
        temp.add(noticePriority41SubTitle);
        temp.add(noticePriority42SubTitle);
        temp.add(noticePriority43SubTitle);
        temp.add(noticePriority44SubTitle);
        temp.add(noticePriority45SubTitle);
        temp.add(noticePriority46SubTitle);
        temp.add(noticePriority47SubTitle);
    }
    // 假设7种场景分别是10%, 10%, 30%, 20%, 10%, 10%, 10%


    public static void main(String[] args) {

        //test5();

    }

    private static void test3() {
        List<Pair<Integer, Double>> list = new ArrayList<Pair<Integer, Double>>();
        list.add(new Pair<Integer, Double>(0, 0.10D));
        list.add(new Pair<Integer, Double>(1, 0.10D));
        list.add(new Pair<Integer, Double>(2, 0.30D));
        list.add(new Pair<Integer, Double>(3, 0.20D));
        list.add(new Pair<Integer, Double>(4, 0.10D));
        list.add(new Pair<Integer, Double>(5, 0.10D));
        list.add(new Pair<Integer, Double>(6, 0.10D));

        WeightRandom weightRandom = new WeightRandom(list);

        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;
        int flag4 = 0;
        int flag5 = 0;
        int flag6 = 0;
        int flag7 = 0;

        int max = 1000;
        int i = 0;
        while (i < max) {
            Object random = weightRandom.random();
            //int index = getProbability3();
            int index = (int) random;
            if (index == 0) {
                flag1 = ++flag1;
            } else if (index == 1) {
                flag2 = ++flag2;
            } else if (index == 2) {
                flag3 = ++flag3;
            } else if (index == 3) {
                flag4 = ++flag4;
            } else if (index == 4) {
                flag5 = ++flag5;
            } else if (index == 5) {
                flag6 = ++flag6;
            } else if (index == 6) {
                flag7 = ++flag7;
                System.out.println(flag7);
            }

            String msg = temp.get(index);
            System.out.println(index + "  " + msg);
            i = ++i;
        }
        double dividend = max;
        System.out.println("概率拟合1 " + (flag1 / dividend));
        System.out.println("概率拟合2 " + (flag2 / dividend));
        System.out.println("概率拟合3 " + (flag3 / dividend));
        System.out.println("概率拟合4 " + (flag4 / dividend));
        System.out.println("概率拟合5 " + (flag5 / dividend));
        System.out.println("概率拟合6 " + (flag6 / dividend));
        System.out.println("概率拟合7 " + (flag7 / dividend));


    }



}

