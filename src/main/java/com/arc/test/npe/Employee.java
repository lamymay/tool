package com.arc.test.npe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.EnumMap;

@Setter
@Getter
@ToString
public class  Employee {
    private int age;
    private String name;

    //测试拆箱中NPE问题
    private static void testSetAge(Integer integer) {
        Employee employee2 = new Employee();
        employee2.setAge(integer == null ? 0 : integer);
        System.out.println(employee2);

        Employee employee = new Employee();
        employee.setAge(integer);
        System.out.println(employee);
    }
    public static void main(String[] args) {

        testSetAge(Integer.valueOf(1));
        testSetAge(null);

//        ArrayList
    }


}
