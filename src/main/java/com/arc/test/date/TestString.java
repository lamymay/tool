package com.arc.test.date;

/**
 * @author May
 * @since 2020/1/13 10:34
 */
public class TestString {

    public static void main(String[] args) {
        String str1 = "1，2，3，，，，，";
        String[] split = str1.split("，");
        String[] split2 = str1.split("，", 0);
        System.out.println(split.length);
        System.out.println(split2.length);

        for (String s : split) {
            System.out.println(s);
        }

    }
}
