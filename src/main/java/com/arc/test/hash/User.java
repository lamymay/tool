package com.arc.test.hash;

/**
 * 测试eqals方法
 *
 * @author may
 * @since 2019/7/15 14:05
 */
class User {
    String name;
    int age;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        //return super.hashCode();
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(age);
        char[] charArr = sb.toString().toCharArray();

        int hash = 0;
        for (char c : charArr) {
            hash = hash * 131 + c;
        }
        return hash;
    }

    /*
     *比较过程思路：
     *1、两个对象指向位置相同，那么他们就相等，return后跳出函数，不再往下执行
     *2、指向位置不同，有3情况
     *a：这两对象类型相同，并且内容相同，也属于相等
     *b：类型相同，内容不同，属于不等;
     *c：类型不同，属于不等
     */
    @Override
    public boolean equals(Object obj) {
        //1、指向位置相同，指定同一对象
        if (this == obj) {
            return true;
        }
        //2、指向位置不同
        //能调用这里的equals，肯定是生成了User对象的，所以this肯定是User类型
        //判断obj是不是User类型，如果是就跟this为同一类型
        if (obj instanceof User) {
            /*
             *类型相同,obj为User类型，而跟this位置不同的对象(第1步已证明)
             *obj来源可能是：
             *User obj = new User();
             *或
             *Object obj =new Object(); //Object是 User的父类
             */

            //obj向下转型为User对象，以便一定能调用User里面的值
            User u = (User) obj;
            //这里的equals是用name调用的(jdk里面String复写过的equals方法)，不是当前类的equals
            //基本数据类型用==比，引用数据类型用equals比
            if (this.age == u.age && this.name.equals(u.name)) {
                return true;
            } else {
                return false;
            }

        } else {
            //类型不同，不是user对象，不等
            return false;
        }
    }

    public static void main(String args[]) {
        //测试一：同地址，相等
        User u1 = new User("sss");
        User u2 = u1;
        boolean b = u1.equals(u2);
        System.out.println(b);

        //测试二：地址不同，类型、内容相同，相等
        User u3 = new User("sss");
        User u4 = new User("sss");
        System.out.println(u3.equals(u4));
        System.out.println(u1.hashCode());//214126413
        System.out.println(u2.hashCode());//214126413
        System.out.println(u3.hashCode());
        System.out.println(u4.hashCode());
        //true
        //true
        //214126413
        //214126413
        //396873410
        //1706234378
    }

}
