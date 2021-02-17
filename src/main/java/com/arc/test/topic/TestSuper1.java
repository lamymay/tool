package com.arc.test.topic;

/**
 * @author 叶超
 * @since 2019/8/25 15:42
 */
public class TestSuper1 {
}

class Base {
    Base() {
        System.out.println("Base");
    }
// 编译报错
//    public class Alpha extends Base {
//        public static void main(String[] args) {
//            Alpha alpha = new Alpha();
//        }
//    }
//

}
