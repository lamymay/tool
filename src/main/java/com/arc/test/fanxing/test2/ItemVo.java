package com.arc.test.fanxing.test2;

/**
 * 这是泛型类
 *
 * @author may
 * @since 2019/7/16 22:47
 */

public class ItemVo {
    protected int id;
    protected int count;

    public ItemVo() {
        // TODO Auto-generated constructor stub
    }

    public ItemVo(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id + " " + count;
    }
}
