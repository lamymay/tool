package com.arc.test.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 叶超
 * @since 2019/7/31 9:56
 */
@RequestMapping("/thread")
@RestController
@Slf4j
public class TestThreadLocal {

    @RequestMapping("/info")
    public Object getMap() {

        return "tt";
    }

    public static void main(String[] args) {
//        fun1();
        System.out.println(fun2());
    }

    private static void fun1() {
        try {
            log.debug("结果={}", Thread.activeCount());
            int a = 1 / 0;
        } catch (Exception e1) {
            log.debug("catch={}", Thread.activeCount());
            e1.printStackTrace();
        } finally {
            log.debug("finally ={}", Thread.activeCount());
        }
    }

    private static int fun2() {
        int a = 0;
        try {
            System.out.println(" 开始");
            a = 12 / 3;
            return a;
        } catch (ArithmeticException e1) {
            e1.printStackTrace();
            a = -1;
            System.out.println(" ArithmeticException " + a);
            return a;
        } catch (Exception e2) {
            System.out.println(" Exception");
            e2.printStackTrace();
        } finally {
            System.out.println(" finally");
            //a = -2;
            System.out.println(" finally " + a);
            // return a;
        }
        return a;

    }
}
