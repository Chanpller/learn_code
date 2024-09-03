package com.chanpller.springframework_6.chapter3;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {
    @Test
    public void test_FactoryBean(){
//
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean-FactoryBean.xml");
        Object user = applicationContext.getBean("user");
        System.out.println(user);
        //User 无参构造器
        //com.chanpller.springframework_6.chapter2.User@5217f3d0
    }
}
