package com.arc.test.thread.pool;

import java.util.concurrent.*;

/**
 * @author may
 * @since 2019/8/5 10:20
 */
public class TestThreadPool2 {


    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                    System.out.println(Thread.currentThread().getName() + " is running");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1));
        System.out.println("----原始状态------");
        System.out.println("核心线程数 " + executor.getCorePoolSize());
        System.out.println("线程池总数 " + executor.getPoolSize());
        System.out.println("队列任务数 " + executor.getQueue().size());

        System.out.println("----开启三个线程------");
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
        System.out.println("核心线程数 " + executor.getCorePoolSize());
        System.out.println("线程池总数 " + executor.getPoolSize());
        System.out.println("队列任务数 " + executor.getQueue().size());

        System.out.println("----再开启三个------");
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
        System.out.println("核心线程数 " + executor.getCorePoolSize());
        System.out.println("线程池总数 " + executor.getPoolSize());
        System.out.println("队列任务数 " + executor.getQueue().size());
        Thread.sleep(8L);
        System.out.println("核心线程数 " + executor.getCorePoolSize());
        System.out.println("线程池总数 " + executor.getPoolSize());
        System.out.println("队列任务数 " + executor.getQueue().size());

    }

}
