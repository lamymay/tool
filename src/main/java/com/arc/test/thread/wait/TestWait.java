package com.arc.test.thread.wait;

/**
 * @author 叶超
 * @since: 2019/8/6 11:01
 */
public class TestWait {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        runnable.run();
        runnable.run();
        runnable.run();
        runnable.run();

    }

}
