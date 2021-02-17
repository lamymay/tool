package com.arc.test.program.test5;


import java.util.Arrays;

/**
 * @author may
 * @since 2019/7/23 22:48
 */
public class Person {

    public static void main(String[] args) {
//        Gun.shoot();
//        int[] array = null;
//        int[] array = new int[0];
        int[] array = new int[1];
        Gun.shoot(array);
    }
}

class Gun {

    static int MAX_SIZE = 5;


    /**
     * 射击
     *
     * @param bullet
     */
    public static void shoot(int[] bullet) {
        checkBullet(bullet);

        int[] usefulBullet = bullet;
        if (bullet.length - 1 <= 0) {
            usefulBullet = push(bullet);
        }
        pop(usefulBullet);
        System.out.println("射击1次成功");

    }


    /**
     * 消耗一个子弹
     *
     * @param bullet
     */
    public static void pop(int[] bullet) {
        if (bullet == null || bullet.length < 0 || bullet.length > 6) {
            throw new RuntimeException("弹夹异常，原因：为空或规格超大");
        }
        System.out.println(bullet.length);

        int[] tempArray = new int[bullet.length - 1];
        for (int i = 0; i < bullet.length - 1; i++) {
            tempArray[i] = bullet[i];
        }
        System.out.println("原来的" + Arrays.toString(bullet));
        System.out.println("装满的" + Arrays.toString(tempArray));
    }

    /**
     * 检查弹夹是否合法
     *
     * @param bullet
     */
    private static void checkBullet(int[] bullet) {
        if (bullet == null || bullet.length < 0 || bullet.length > 6) {
            throw new RuntimeException("弹夹异常，原因：为空或规格超大");
        }
    }

    /**
     * 默认数组内压入数字0，如果子弹定义为其他什么数字，请自己给加上数组赋值的逻辑
     *
     * @param bullet
     */
    public static int[] push(int[] bullet) {
        if (bullet == null || bullet.length == 0) {
            bullet = new int[MAX_SIZE];
            return bullet;
        } else {
            if (bullet.length > MAX_SIZE) {
                throw new RuntimeException("弹夹异常，原因：规格超大,超过" + MAX_SIZE);
            }
        }
        //装弹
        int[] tempArray = new int[MAX_SIZE];

        for (int i = 0; i < bullet.length - 1; i++) {
            System.out.println(i);
            System.out.println(bullet.length);
            System.out.println(tempArray.length);
            tempArray[i] = bullet[i];
        }
        //todo 如果子弹不是用0 表示，那么你需要把  tempArray.length - bullet.length  填充一下

        //现在数据内的元素为： （5个）
        System.out.println("原来的" + Arrays.toString(bullet));
        System.out.println("装满的" + Arrays.toString(tempArray));
        return tempArray;
    }

}
