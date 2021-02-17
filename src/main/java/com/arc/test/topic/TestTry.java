package com.arc.test.topic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 叶超
 * @since 2019/7/31 9:56
 */
@Slf4j
public class TestTry {


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

//总结：
//try/catch 中有return，在程序执行过程中，会跳过return执行finally块语句，而后执行try/catch块的return
//finally 中有return，在程序执行过程中，则执行到finally块的return时，直接return，try/catch块中的return被忽略未被执行

//java语言规范中规定，另外，在java的语言规范有讲到，如果在try语句里有return语句，finally语句还是会执行。它会在把控制权转移到该方法的调用者或者构造器前执行finally语句。也就是说，使用return语句把控制权转移给其他的方法前会执行finally语句。

//翻译：
//
//如果try语句里有return，那么代码的行为如下：
//
//1.如果有返回值，就把返回值保存到局部变量中
//
//2.执行jsr指令跳到finally语句里执行
//
//3.执行完finally语句后，【返回之前保存在局部变量表里的值，对应的是第一中情况，finally块中队变量修改了，自己不返回，跳转到try catch块，他们获得的变量是变化后的，但是返回的时候将返回跳入finally块前的值，Test 例子中88行断点可看   】


//什么时候finally块不会执行？
//当try或者catch的代码在运行的时候，JVM退出了（例如调用System.exit()或者非正常的退出）。那么finally语句块就不会执行。同样，如果线程在运行try或者catch的代码时被中断了或者被杀死了(killed)，那么finally语句可能也不会执行了，即使整个运用还会继续执行。正如stackoverflow里这样的一段描述：
//
//The only time finally won’t be called is: if you call System.exit(), another thread interrupts current one, or if the JVM crashes first.
// ————————————————
//版权声明：本文为CSDN博主「seven_books」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/qq_34741578/article/details/89526252

class Test {
    public int aaa() {
        int x = 1;

        try {
             ++x;
            return x;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ++x;
            System.out.println("finally "+x);
        }
        return x;
    }

    public static void main(String[] args) {
        Test t = new Test();
        int y = t.aaa();
        System.out.println(y);
    }
}
