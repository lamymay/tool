package com.arc.test.program.test1;

/**
 * @author may
 * @since 2019/7/19 8:54
 */
public class Test1 {
    public static void main(String[] args) {
        Integer superLength = new Super1().getLength();
        Integer subLength = new Sub1().getLength();
        System.out.println(superLength + "--"+subLength
        );
    }
}
