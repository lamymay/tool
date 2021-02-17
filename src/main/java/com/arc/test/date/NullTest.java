package com.arc.test.date;

import lombok.Data;

/**
 * @author yechao
 * @since 2020/6/12 9:40 上午
 */
public class NullTest {

    @Data
    static
    class User {
        private  String username;
        private  String pasword;
        private int age;

    }

    public static void main(String[] args) {
        User user = null;
        if (user == null) {
            System.out.println("null object 可以判断是否是null");
        }
        System.out.println(user);

        user = new User();
        System.out.println(user);


    }


}
