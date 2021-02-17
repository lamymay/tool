package com.arc.test.light;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficSignalMain {
    //红绿灯的标志0-20表示红灯,21 - 23 表示黄灯, 24 - 50 表示绿灯
    private static Integer signalNum = 1;

    private static boolean isContinue = true;
    private Lock lock = new ReentrantLock();
//    private Condition notFull = lock.newCondition();
//    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {


        //开启线程1运行
        TrafficSignalMain trafficSignalMain = new TrafficSignalMain();
        RunSignalThread runSignalThread = trafficSignalMain.new RunSignalThread();
        ChanSignal chanSignal = trafficSignalMain.new ChanSignal();
        runSignalThread.start();
        //开启线程2,运行输入控制程序
        chanSignal.start();
    }

    class ChanSignal extends Thread {

        public void run() {
            while (true) {
                //必须初始化
                        try {
                            System.out.print("输入");
                            Scanner scan = new Scanner(System.in);
                            String read = scan.nextLine();
                            System.out.println("输入数据："+read);
                            if (read != null) {
                                signalNum = Integer.valueOf(read);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class RunSignalThread extends Thread {

        public void run() {
            while (true) {
                if (signalNum >= 0 && signalNum <= 20) {
                    System.out.println("红灯");
                    try {
                        Thread.sleep(1000);
                        signalNum++;
                        System.out.println(signalNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (signalNum >= 21 && signalNum <= 23) {
                    System.out.println("黃灯");
                    try {
                        Thread.sleep(1000);
                        signalNum++;
                        System.out.println(signalNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (signalNum >= 24 && signalNum <= 50) {
                    System.out.println("绿灯");
                    try {
                        Thread.sleep(1000);
                        signalNum++;
                        System.out.println(signalNum);
                        if (signalNum == 51) {
                            signalNum = 1;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (signalNum > 51) {
                    System.out.println("停止工作");
                }

            }
        }
    }
}

