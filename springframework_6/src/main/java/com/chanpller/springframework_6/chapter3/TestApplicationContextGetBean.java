package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter2.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class TestApplicationContextGetBean {
    private Logger logger = LoggerFactory.getLogger(TestApplicationContextGetBean.class);
    @Test
    public void testGetBeanById(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        User userById = (User) applicationContext.getBean("user");
        System.out.println(userById);
        userById.add();

        User userByClass = applicationContext.getBean(User.class);
        System.out.println(userByClass);
        userByClass.add();

        //如果id不存在或者类型不一致会报异常
        User userByIdAndClass =  applicationContext.getBean("user",User.class);
        System.out.println(userByIdAndClass);
        userByIdAndClass.add();
    }


}
