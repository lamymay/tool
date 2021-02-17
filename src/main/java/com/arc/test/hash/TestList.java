package com.arc.test.hash;

import java.util.HashMap;
import java.util.List;

/**
 * @author may
 * @since 2019/7/16 15:34
 */
public class TestList {


    public static void main(String[] args) {
        HashMap<Integer, List<User>> map = new HashMap<>();
//        map.get()

        int i = 24 / 12;
        System.out.println(i);

        double b = (double) 3 / (double) 12;
        System.out.println(b);
        int c = (int) (((double) 9 / (double) 10) * 1000);
        System.out.println(c);

        int f = (int) (((float) 9 / (float) 10) * 1000);
        System.out.println(f);
    }
}
