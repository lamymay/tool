package com.arc.test.thread.wait;

/**
 * @author 叶超
 * @since: 2019/8/6 11:01
 */
public class TestYield {

    public static void main(String[] args) {
        //准备一个新的线程
        System.out.println(System.currentTimeMillis() + "线程" + Thread.currentThread().getName() + "执行！");

        Thread t1 = new MyThread1();
        t1.start();
        System.out.println(System.currentTimeMillis() + "线程" + Thread.currentThread().getName() + "执行！");

        for (int i = 0; i < 20; i++) {
            System.out.println(System.currentTimeMillis() + "线程" + Thread.currentThread().getName() + "第" + i + "次执行！");
            if (i > 2) try {
                //t1线程合并到主线程中，主线程阻塞执行过程，转而执行t1线程，直到t1执行完毕后继续。
                System.out.println(System.currentTimeMillis() + "线程" + Thread.currentThread().getName() + "第" + i + "次执行！");
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread1 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(System.currentTimeMillis() + "线程" + Thread.currentThread().getName() + "第" + i + "次执行！");

        }
    }
}