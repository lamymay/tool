package com.arc.test.autwired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author may
 * @since 2019/7/16 21:26
 */
@Component("b")
public class B {
    @Autowired
    private A  a;

    public void sayByB() {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().hashCode());
        System.out.println(a);
        System.out.println("---------------------------------B");

        TestService aService = a.getTestService();
        System.out.println(aService);
        System.out.println(aService.hashCode());

        System.out.println(a.getTestService());
        System.out.println(a.getTestService());
        System.out.println(a.getTestService());

    }

}

