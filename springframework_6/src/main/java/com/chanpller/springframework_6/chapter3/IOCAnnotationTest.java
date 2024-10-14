package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter3.annotation.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCAnnotationTest {
    @Test
    public void test_singleton() {
//
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.chanpller.springframework_6.chapter3.annotation.impl");
        UserService service = applicationContext.getBean("userServiceImpl", UserService.class);
        service.save();
    }
}
