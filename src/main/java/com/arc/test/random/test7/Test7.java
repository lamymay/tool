package com.arc.test.random.test7;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yechao
 * @since 2020/7/15 4:11 下午
 */
public class Test7 {


    public static void main(String[] args) {
        double temp = testFubAQuarter();
        System.out.println(temp);

    }


    //默认数据
    public static List<Double> DEFAULT_LIST = new ArrayList<>();

    //最大循环次数
    private static final int MAX_COUNT = 10000;

    static {
        DEFAULT_LIST.add(0.25D);
        DEFAULT_LIST.add(0.125D);
        DEFAULT_LIST.add(0.50D);
        DEFAULT_LIST.add(0.75D);
    }

    /**
     * @return 获取一个能被0.25整除的数
     */
    private static double testFubAQuarter() {
        int count = 0;

        while (count < MAX_COUNT) {
            count = count + 1;
            //[1,10000] 闭区间
            int nextInt = RandomUtils.nextInt(1, 10001);
            if (nextInt % 25 == 0) {
                return nextInt / 10000D;
            }
        }
        return DEFAULT_LIST.get(RandomUtils.nextInt(0, 3));
    }
}
