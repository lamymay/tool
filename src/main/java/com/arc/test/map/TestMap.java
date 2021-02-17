package com.arc.test.map;

import java.util.HashMap;

/**
 * @author may
 * @since 2019/7/26 11:11
 */
public class TestMap {

    public static void main(String[] args) {

        HashMap<String, Object> map = new HashMap<>();
        Object a = map.put("a", 1111);

        Object a1 = map.put("a", 1100);
        Object b = map.put("b", 0010);


        System.out.println(a);
        System.out.println(a1);
        System.out.println(b);
        System.out.println(map.get("a"));
        System.out.println(map.get(null));

    }
}
