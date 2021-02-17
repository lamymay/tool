package com.arc.test.thread.call;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author 叶超
 * @since: 2019/8/5 11:31
 */
public class TestCallable implements Callable<Object> {
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"  implements Callable<Object>");
        return null;
    }
}

class TestCall{
    public static void main(String[] args) throws InterruptedException {
        TestCallable testCallable = new TestCallable();
        FutureTask<Object> objectFutureTask = new FutureTask<>(testCallable);
        Thread thread = new Thread(objectFutureTask);
        System.out.println(Thread.currentThread().getName());
        thread.start();
        thread.join();


    }
}