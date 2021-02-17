package com.arc.test;

import java.util.*;

/**
 * 失败的
 *
 * @author yechao
 * @since 2020/6/18 3:03 下午
 */
public class MyTestList {


    public ArrayList<List> list = new ArrayList<List>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList("1", "1")),
                    new ArrayList<>(Arrays.asList("2", "2", "2")),
                    new ArrayList<>(Arrays.asList("3", "3", "3", "3"))
            ));


    //    Users/may/.m2/repository/com/alibaba/alisports/activity/
    public static void main(String[] args) {
        MyTestList myTestList = new MyTestList();
        while (true) {
            //循环前打印长度
            System.out.println(myTestList.list.get(0).size());
            System.out.println(myTestList.list.get(1).size());
            System.out.println(myTestList.list.get(2).size());
            Random random = new Random();
            int i = random.nextInt(3);
            System.out.println("现在要删除的list为:" + i);
            ListIterator iterator = myTestList.list.get(i).listIterator();
            //删除前判断长度
            if (myTestList.list.get(i).size() <= 0) {
                for (int j = 0; j <= i + 1; j++) {
                    iterator.add(i + 1);
                }
            } else {
                System.out.println("删除的元素" + iterator.next());
                iterator.remove();
            }
        }
    }


}