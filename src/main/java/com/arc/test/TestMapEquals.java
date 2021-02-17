package com.arc.test;

/**
 * @author may
 * @since 2019/7/8 23:20
 */

import java.util.HashMap;
import java.util.Map;

//Map中key值不可重复的测试
public class TestMapEquals {

    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = new String("abc");

        Map map = new HashMap();
        map.put(s1, "abc123");
        map.put(s2, "ABC456");//第二个会覆盖第一个元素,这里调用的对象的equals方法

        //注意：map中key值不可重复，直接根据比较的是equals,只有equals相同则覆盖
        System.out.println(map.size());//1
        System.out.println(map.get(s1));//ABC456
        System.out.println(s1==s2);//false
        System.out.println(s1.equals(s2));//true

        System.out.println(s1);//abc
        System.out.println(s2);//abc
        System.out.println(s1.hashCode());//96354
        System.out.println(s2.hashCode());//96354
    }
//输出结果：
// 1
//ABC456
//false
//true
}
