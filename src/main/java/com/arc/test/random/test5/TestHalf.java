package com.arc.test.random.test5;

import org.apache.commons.lang3.RandomUtils;

import java.util.*;

/**
 * 概率可调的测试
 */
public class TestHalf {


    public static void main(String[] args) {
        int i = 0;
        int max = 100;
        while (i < max) {
            i = ++i;
            System.out.println("index=" + getProbability4());
        }

    }


    private static int test1() {
        int low = 0;
        int high = 100;
        //int total = 7;
        List<Float> originProbability = Arrays.asList(0.2F, 0.2F, 0.3F, 0.3F, 0.1F, 0.1F, 0.1F);
        List<Float> trueProbability = new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();

        int size = originProbability.size();


        float 原始概率 = 0;

        for (int i = 0; i < size; i++) {
            Float aFloat = originProbability.get(i) * 100;
            float v = aFloat / size;
            原始概率 += v;
            System.out.println("单项的概率是v=" + v);
            trueProbability.add(v);

            int step = (int) (100 * v);
            if (map.get(0) == null) {
                map.put(i, getContainer(low, step));//0.1
            } else {
                List<Integer> list = map.get(i - 1);
                Integer max = list.get(list.size() - 1);
                int stepHigh = max + step;
                map.put(i, getContainer(max + 1, stepHigh));//0.1
            }
        }

        System.out.println("原始概率和=" + 原始概率);

        float totalP = 0;
        for (Float p : trueProbability) {
            totalP += p;
        }
        System.out.println("原始的单项概率=" + originProbability);
        System.out.println("计算后单项概率=" + trueProbability);
        System.out.println("计算后总项概率 totalP=" + totalP);


        int one = RandomUtils.nextInt(low, high);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> integerList = entry.getValue();
            if (integerList.contains(one)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("没有匹配到");
    }

    private static int getProbability4() {
        int low = 1;
        int high = 4;
        float 原始概率 = 0;

//        List<Integer> probability = Arrays.asList(10, 10, 10, 10, 10, 10, 10);
        List<Integer> probability = Arrays.asList(10, 10, 30);
        Map<Integer, List<Integer>> map = new HashMap<>();

        int start = 0;
        for (Integer step : probability) {
            if (start ==0) {
                map.put(start, getContainer(1, step));
            }else {
                List<Integer> list = map.get(start-1);
                low = list.get(list.size() - 1) + 1;
                map.put(start, getContainer(low, low + 9));
            }
            start = ++start;

        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }

        int one = RandomUtils.nextInt(1, 30);
        System.out.println("one ----- " + one);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> integerList = entry.getValue();
            if (integerList.contains(one)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("没有匹配到");
    }


    private static List<Integer> getContainer(int low, int high) {
        List<Integer> list = new ArrayList<>();
        while (low <= high) {
            list.add(low);
            low = ++low;
        }
        System.out.println(list);
        return list;
    }

}
