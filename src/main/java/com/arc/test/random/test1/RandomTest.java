package com.arc.test.random.test1;

import org.apache.commons.lang3.RandomUtils;

import java.util.*;

/**
 * 测试概率可调整的数据取值
 *
 * @author yechao
 * @since 2020/6/18 3:06 下午
 */
public class RandomTest {


    //100 个数
    //private static List<Integer> tempContainer = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100);

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

    // 个数 = 100/百分比基准数,  当以X%为基准时, 以X%为一个刻度划分, 最小可以以1%作为基准
    private static List<Integer> aContainer = Arrays.asList(9, 10, 11, 11, 11, 12, 12, 13, 14, 15);

    public static Integer getRandomInt() {
        return aContainer.get((int) (Math.random() * 10)) % 8;
    }

    public static void main(String[] args) {

        //test1();
        //test2();
        //test4();


        //test3();
        test5();

    }

    private static void test5() {
        int max = 1000;
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;
        int flag4 = 0;
        int flag5 = 0;
        int flag6 = 0;
        int flag7 = 0;

        for (int i = 0; i < max; i++) {
            int index = getProbability4();
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
            }

            String msg = temp.get(index);
            System.out.println(msg);
        }
        float temp = max;
        System.out.println("概率拟合1 " + (flag1 / temp));
        System.out.println("概率拟合2 " + (flag2 / temp));
        System.out.println("概率拟合3 " + (flag3 / temp));
        System.out.println("概率拟合4 " + (flag4 / temp));
        System.out.println("概率拟合5 " + (flag5 / temp));
        System.out.println("概率拟合6 " + (flag6 / temp));
        System.out.println("概率拟合7 " + (flag7 / temp));


    }

//    private static void test4() {
//        //        while (true) {
////            int next  = RandomUtils.nextInt(1, 101);
//        float next = RandomUtils.nextFloat(0.01F, 1.0F);
////        System.out.println(next);
////        }
//    }

    private static void test3() {

        int total = temp.size();//7

        for (int i = 0; i < 10; i++) {
            int index = getProbability2(100);
            System.out.println(index);

        }

//        String msg = temp.get(index);
//        System.out.println(msg);


    }

    private static int getProbability3() {
        int low = 0;
        int high = 100;

        ArrayList<List<Integer>> forIndexList = new ArrayList<>(16);
        //1 2 3 4 5 6 7 概率各自是 ? 总概率是 1
        int total = 7;

        forIndexList.add(getContainer(low, 10));//0.1
        forIndexList.add(getContainer(11, 30));//0.2
        forIndexList.add(getContainer(31, 50));//0.2
        forIndexList.add(getContainer(51, 90));//0.4
        forIndexList.add(getContainer(51, 90));//0.4
        forIndexList.add(getContainer(51, 90));//0.4
        forIndexList.add(getContainer(91, high));//0.1

        System.out.println("forIndexList=" + forIndexList.size());
        System.out.println(forIndexList.get(0));
        System.out.println(forIndexList.get(1));
        System.out.println(forIndexList.get(2));
        System.out.println(forIndexList.get(3));
        System.out.println(forIndexList.get(4));


        int one = RandomUtils.nextInt(low, high);
        for (int i = 0; i < forIndexList.size(); i++) {
            if (forIndexList.get(i).contains(one)) {
                return i;
            }
        }
        throw new RuntimeException("没有匹配到");
    }

    private static int getProbability4() {
        int low = 0;
        int high = 100;
        //int total = 7;
        List<Float> originProbability = Arrays.asList(0.2F, 0.2F, 0.3F, 0.3F, 0.1F, 0.1F, 0.1F);
        List<Float> trueProbability = new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();

        int size = originProbability.size();


        float 原始概率 = 0;

        for (int i = 0; i <size ; i++) {
            Float aFloat = originProbability.get(i)*100;
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

    @Deprecated
    private static int getProbability40() {
        int low = 0;
        int high = 100;
        Map<Integer, List<Integer>> map = new HashMap<>();

        map.put(1, getContainer(low, 10));//0.1
        map.put(2, getContainer(11, 30));//0.2
        map.put(3, getContainer(31, 60));//0.3
        map.put(4, getContainer(61, high));//0.4

        int one = RandomUtils.nextInt(low, high);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> integerList = entry.getValue();
            if (integerList.contains(one)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("没有匹配到");
    }


    private static int getProbability2(int total) {

        //Node node1 = new Node(0.1F);
//        Node node2 = new Node(0.2F);
//        Node node3 = new Node(0.3F);
//        Node node4 = new Node(0.1F);
//        Node node5 = new Node(0.1F);
//        Node node6 = new Node(0.1F);
//        Node node7 = new Node(0.1F);

//        Node node3 = new Node();
//        Node node4 = new Node();
//        Node node5 = new Node();
//        Node node6 = new Node();
//        Node node7 = new Node();

//        float p3=0.1F;
//        float p4=0.1F;
//        float p5=0.1F;
//        float p6=0.1F;
//        float p7=0.1F;

        Node node1 = new Node();
        Node node2 = new Node();

        float p1 = 0.1F;
        float p2 = 0.9F;

        node1.setLow(0);
        node1.setHigh((int) (total * p1));
        node1.setWeight(p1);

        node2.setLow(node1.getHigh());
        node2.setHigh(node1.getHigh() + (int) (total * p2));
        node2.setWeight(p2);

        int index = RandomUtils.nextInt(node1.getLow(), node2.getHigh());


        return index;

    }

    private static void test2() {
        float p = 20 / 100F;
        int probability = getProbability(p);

        int index = 1;
        System.out.println("index=" + index + "   " + temp.get(index));
    }

    /**
     * //容器分段, 接受传入指定概率小数
     * int tempIndexHigh = (int) (100 * p);
     *
     * @param p
     * @return
     */
    public static int getProbability(float p) {


        //100
        List<Integer> c10 = getContainer(1, 10);

        List<Integer> c11_30 = getContainer(11, 30);


        //随机数
        //float nextFloat = RandomUtils.nextFloat(0.01F, 1.0F);
        int next = RandomUtils.nextInt(1, 101);

        //取出来3次 概率就大一些

        return 1;
    }

    private static void test1() {
        float probability1 = getRandomProbability();
        float probability2 = getRandomProbability2();
        float probability3 = getRandomProbability3();
        System.out.println(1);
        //分母
        int denominator = 100;

        //分子
        int numerator1 = (int) (denominator * probability1);
        int numerator2 = (int) (denominator * probability2);
        int numerator3 = (int) (denominator * probability3);


        List<Integer> tempList = getContainer();
        Integer var1 = tempList.get(numerator1);
        Integer var2 = tempList.get(numerator2);
        Integer var3 = tempList.get(numerator3);

        System.out.println("概率1=" + probability1 + " 分子1=" + numerator1 + " var1=" + var1);
        System.out.println("概率2=" + probability2 + " 分子2=" + numerator2 + " var2=" + var2);
        System.out.println("概率3=" + probability3 + " 分子3=" + numerator3 + " var3=" + var3);
    }

    //获取随机概率
    private static float getRandomProbability() {
        return 1 / 10F;
    }

    private static float getRandomProbability2() {
        return 2 / 10F;
    }

    private static float getRandomProbability3() {
        return 3 / 10F;
    }

    private static List<Integer> getContainer() {
        int i = 0;
        List<Integer> list = new ArrayList<>();
        while (i <= 100) {
            list.add(i);
            i = ++i;
        }
        System.out.println(list);
        return list;
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

