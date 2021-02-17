package com.arc.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author {author}
 * @since 2019/12/7 22:46
 */
public class TestStringEqualsHashCode {
    public static void main(String[] args) {
        String str1 = "通话";
        String str2 = "重地";
        System.out.println(String.format("str1：%d | str2：%d", str1.hashCode(), str2.hashCode()));
        System.out.println(str1.equals(str2));

        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());

        HashMap<String, Object> map = new HashMap<>();
        map.put(str1, str1);
        map.put(str2, str2);

        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            System.out.println(stringObjectEntry.getKey() + "--" + stringObjectEntry.getValue());
        }

        HashSet<String> set = new HashSet<>();
        set.add(str1);
        set.add(str2);
        for (String s : set) {
            System.out.println(s);
        }

    }
}
