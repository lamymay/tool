package com.arc.test.fanxing.test1;

/**
 * @author may
 * @since 2019/7/16 22:25
 */
public class Model {

    private Long id;
    private String name;

    public Model(long id, String name) {
        this.id = id;
        this.name= name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
