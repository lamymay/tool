package com.arc.test.topic;

/**
 * @author 叶超
 * @since 2019/8/25 18:39
 */
public interface TestInterface {
    void a();

    public double b();

    //标准的，默认修饰符
    public abstract double c();

    //编译失败 public final double c();

    //编译失败  static  void d(double p1);

    //编译失败 protected void e(double p1);

    //注意版本
    default void f(double p1) {
    }

    static void g(double p1) {
        System.out.println(p1 + "静态方法，只能通过接口名调用，不可以通过实现类的类名或者实现类的对象调用。default方法，只能通过接口实现类的对象来调用。");
    }

    //protected void h();
    //private    void i();


    String $a = null;
    //String 12;
    //String abc.ef;


}
