package com.arc.test.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 叶超
 * @since: 2019/8/5 11:09
 */
public class TestAtomicInteger {

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            new Thread() {
                public void run() {
                    count.getAndIncrement();
                    System.out.println(Thread.currentThread().getName() + " is running");
                }
            }.start();
        }

        Thread.sleep(2000L);
        System.out.println(count);

    }


}
