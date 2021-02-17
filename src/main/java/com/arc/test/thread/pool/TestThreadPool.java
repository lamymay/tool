package com.arc.test.thread.pool;

import java.util.concurrent.*;

/**
 * @author may
 * @since 2019/8/5 10:20
 */
public class TestThreadPool {


    public static void main(String[] args) {
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

        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
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


    }

    private static void fun1() {
        int corePoolSize = 3;
        int maximumPoolSize = 6;
        long keepAliveTime = 3;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3);

        new ThreadPoolExecutor(
                corePoolSize, //核心线程数：会一直存活， 不受存活时间限制
                maximumPoolSize,//最大线程数
                keepAliveTime,//闲置线程存活时间
                unit,//时间单位
                workQueue,//线程等待队列
                Executors.defaultThreadFactory(),//线程工厂
                new ThreadPoolExecutor.AbortPolicy()//队列已满，而且当前线程数已经超过最大线程数时的异常处理
        );


        //线程等待队列
        //SynchronousQueue
        //ArrayBlockingQueue
        //LinkedBlockingDeque
    }

}
