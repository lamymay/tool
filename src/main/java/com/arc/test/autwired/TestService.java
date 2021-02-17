package com.arc.test.autwired;

import org.springframework.stereotype.Component;

/**
 * @author may
 * @since 2019/7/16 21:30
 */
@Component
public class TestService {

    public void say() {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().hashCode());
    }

}
