package com.arc.test.string;

/**
 * @author yechao
 * @since 2020/7/8 10:55 上午
 */
public class StringTest {


    public static void main(String[] args) {
        String pack = "com.alibaba.alisports.activity.client.hsf";
        String unixPath = pack.replaceAll("\\.", "/");
        System.out.println();

        //com/alibaba/alisports/activity/client/hsf

        // cd ~/.m2/repository/

        String path = "/Users/may/.m2/repository/" + unixPath;
        System.out.println(path); //cd /Users/may/.m2/repository/com/alibaba/alisports/activity/client/hsf

        boolean result = cleanMaven(unixPath);
        //你说向我们20多岁
        //除了上班,其他时间是应该做点啥? 学习-学习啥
    }

    private static boolean cleanMaven(String path) {
        // open bash
        //ls path
        // rm -fr path/*
        //ls path


        return false;
    }

}
