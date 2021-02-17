package com.arc.test.thread.test1;

/**
 * @author may
 * @since 2019/7/24 11:49
 */
public class Thread1RunTest  implements Runnable {


    @Override
    public void run() {
        try {
            Thread.sleep(1000L);
            System.out.println("sleep 1000ms ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getClass().getSimpleName()+"--"+this);
    }

    public static void main(String[] args) {

        long s1 = System.currentTimeMillis();
        //线程方式启动子线程去执行方法
        new Thread(new Thread1RunTest()).start();
        new Thread(new Thread1RunTest()).start();

        //方法调用
        //new Thread1RunTest().run();
        //new Thread1RunTest().run();
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);

    }

}

//设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1。写出程序。
//
//    以下程序使用内部类实现线程，对j增减的时候没有考虑顺序问题。
class ThreadTest1 {
    private int j;

    public static void main(String args[]) {
        ThreadTest1 threadTest1 = new ThreadTest1();
        A inc = threadTest1.new A();
        B dec = threadTest1.new B();

        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(inc);
            t.start();
            t = new Thread(dec);
            t.start();
        }
    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }

    class A implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                inc();
            }
        }
    }

    class B implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                dec();
            }
        }
    }
}
