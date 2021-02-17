package com.arc.test.date;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author yechao
 * @since 2020/6/1 7:09 下午
 */
public class DateTest2 {

    public static void main(String[] args) throws Exception {

//        fun1();
        //fun2();
        //float randomProbability = randomProbability(10);
        for (int i = 0; i < 100; i++) {

            System.out.println(randomOne(41,47));
        }
    }


    private static  int randomOne(int low, int high) {
        int randomInt = RandomUtils.nextInt(low,high+1);
        return randomInt;
    }


    /**
     * 指定概率
     *
     * @param probability
     * @return
     */
    public static float randomProbability(int probability) {



        int randomInt = RandomUtils.nextInt(1, 101);

        // 3/10 =0.3   5/100
        float temp = randomInt / 100.0F;
        System.out.println(temp);
        return temp;
    }


    private static void fun1() {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 10);
        m.put(1, 20);
        m.put(2, 30);
        m.put(3, 40);

        int[] h2 = new int[4];
        for (int i = 0; i < h2.length; i++) {
            h2[i] = 1;
        }
        Random random = new Random();
        float round = 20000F;

        for (int x = 0; x < round; x++) {
            int i = 0;
            int j = 0;
            int r = random.nextInt(99);
            for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
                j = i + entry.getValue() - 1;
                if (r >= i && r <= j) {
                    h2[entry.getKey()]++;
                    break;
                }
                i += entry.getValue();
            }
        }

        for (int i : h2) {
            System.out.printf("%f   ", i / round);
        }
    }

    private static void fun2() throws Exception {
        Date now = new Date();

        String pattern = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(pattern);

        Date tempDate = dateFormat.parse("2020-06-01");
        Date yesterdayDte = DateUtils.addDays(tempDate, -1);
        System.out.println("tempDate=" + tempDate.toLocaleString());
        System.out.println("now=" + now.toLocaleString());
        System.out.println("yesterdayDte=" + yesterdayDte.toLocaleString());

    }

}
