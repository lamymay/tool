package com.arc.test.program.test2;

/**
 * @author may
 * @since 2019/7/19 9:13
 */
public class TestFloat {


    public Object test1() {
        Object o = new Float(3.14F);
        Object[] oa = new Object[1];
        oa[0] = o;
        o = null;
        oa[0] = null;
        System.out.println(o);
        System.out.println(oa);
        return null;
    }
    //o 应该在14行后才可能被gc
}
