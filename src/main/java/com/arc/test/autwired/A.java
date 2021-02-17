package com.arc.test.autwired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author may
 * @since 2019/7/16 21:26
 */
@Component("a")
public class A {

    @Autowired
    private TestService testService;

    public void sayByA() {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().hashCode());
        System.out.println("---------------------------------A");
    }

    public TestService getTestService() {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().hashCode());
        System.out.println("---------------------------------A");
        return testService;
    }
}

