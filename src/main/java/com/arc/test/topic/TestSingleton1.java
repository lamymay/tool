package com.arc.test.topic;

/**
 * @author 叶超
 * @since 2019/8/25 15:45
 */
public class TestSingleton1 {
    private volatile TestSingleton1 testSingleton1 = null;

    //这个是懒汉形式的加强版，将synchronized关键字移到了 newInstance 方法里面，
// 同时将singleton对象加上volatile关键字，这种方式既可以避免多线程问题，又不会降低程序的性能。
// 但volatile关键字也有一些性能问题，不建议大量使用。


//下面代码就是用double checked locking 方法实现的单例，
// 这里的getInstance()方法要检查两次，确保是否实例INSTANCE是否为null或者已经实例化了，这也是为什么叫double checked locking 模式。

    public TestSingleton1 getInstance() {
        if (testSingleton1 == null) {
            synchronized (TestSingleton1.class) {
                if (testSingleton1 == null) {
                    testSingleton1 = new TestSingleton1();
                }
            }
        }
        return testSingleton1;
    }


}
