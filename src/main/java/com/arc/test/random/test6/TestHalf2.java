package com.arc.test.random.test6;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yechao
 * @since 2020/6/30 3:31 下午
 */
public class TestHalf2 {


    static Map<Boolean, List<Integer>> tempMap;

    static {
        tempMap = new HashMap<>();
        tempMap.put(true, getContainer(1, 50));//[1,50]
        tempMap.put(false, getContainer(51, 100));//[51,100]
    }


    private static List<Integer> getContainer(int low, int high) {
        List<Integer> list = new ArrayList<>();
        while (low <= high) {
            list.add(low);
            low = ++low;
        }
        return list;
    }


    public static void main(String[] args) {
        fun1(1);
        fun1(2);

    }

    private static void fun1(int halfProbability) {
        long t1 = System.currentTimeMillis();
        int i = 0;
        int a = 0;
        int b = 0;
        float max = 1000_000F;


        while (i < max) {
            i = ++i;
            boolean result;
            if (halfProbability == 1) {
                result = getHalfProbability();
            } else {
                result = getHalfProbability2();
            }
            //测试多次去平均值
            if (result) {
                a = a + 1;
            } else {
                b = b + 1;
            }
        }


        System.out.println(a);
        System.out.println(b);
        System.out.println(a / max);
        System.out.println(b / max);
        long t2 = System.currentTimeMillis();
        System.out.println("fun cost=" + (t2 - t1));

        System.out.println();
    }

    /**
     * 耗时较大125ms 循环1000000次
     *
     * @return
     */
    private static boolean getHalfProbability() {
        int randomInt = RandomUtils.nextInt(1, 101);
        for (Map.Entry<Boolean, List<Integer>> entry : tempMap.entrySet()) {
            List<Integer> integerList = entry.getValue();
            if (integerList.contains(randomInt)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("产生0.5的概率出错");
    }


    /**
     * 耗时较大19ms 循环1000000次
     *
     * @return
     */
    private static boolean getHalfProbability2() {
        int randomInt = RandomUtils.nextInt(1, 101);
        return randomInt < 51;
    }


}
