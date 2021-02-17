package com.arc.test.random.test3byutil;


//import com.cookingfox.guava_preconditions.Preconditions;

import com.google.common.base.Preconditions;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author yechao
 * @since 2020/6/23 12:21 下午
 */

public class WeightRandom<K, V extends Number> {

    //因为TreeMap底层用红黑树实现，TreeMap中的key是唯一且有序的，因此TreeMap提供了如下方法：
    //试想，当我们用普通的不可动态扩展的方法如if/switch方法实现时，必然是产生一个随机数，然后判断该随机数在哪个概率区间。TreeMap因为有序，因此刚好提供了两个方法tailMap，firstKey用于实现落点区间判断的逻辑，简洁又高效，并且方便动态扩展。
    // //注意：TreeMap为异步的非线程安全的，因此在并发要求高时，可以使用如下代码加锁。
    //https://bigjun2017.github.io/2019/05/19/hou-duan/java/dai-quan-chong-de-sui-ji-shu-suan-fa-de-shi-xian/
    //public static Map<Double, K> treemap = Collections.synchronizedMap(new TreeMap<Double, K>());
    //具体的代码实现：
    private TreeMap<Double, K> weightMap = new TreeMap<Double, K>();

    public WeightRandom(List<Pair<K, V>> list) {
        Preconditions.checkNotNull(list, "list can NOT be null!");
        for (Pair<K, V> pair : list) {
            double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey().doubleValue();//统一转为double
            this.weightMap.put(pair.getValue().doubleValue() + lastWeight, pair.getKey());//权重累加
        }
        System.out.println("------Tree init OK!--------");
    }

    public K random() {
        double randomWeight = this.weightMap.lastKey() * Math.random();
        SortedMap<Double, K> tailMap = this.weightMap.tailMap(randomWeight, true);
        return this.weightMap.get(tailMap.firstKey());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {
        test3();

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
//        int i = 0;
//
//        while (i < 100) {
//            ++i;
//            Object random = weightRandom.random();
//            System.out.println(i + " key is " + random);
//
//        }


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

            int index = (int) random;
//            int index = getProbability3();
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