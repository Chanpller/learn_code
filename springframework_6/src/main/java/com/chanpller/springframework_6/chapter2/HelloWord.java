package com.chanpller.springframework_6.chapter2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class HelloWord {
    private Logger logger = LoggerFactory.getLogger(HelloWord.class);
    @Test
    public void testNewUserBySpringXmlApplicationContent(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        User user = (User) applicationContext.getBean("user");
        user.add();
    }

    @Test
    public void testNewUserByReflex() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // dom4j解析beans.xml文件，从中获取class属性值，类的全类名
        // 通过反射机制调用无参数构造方法创建对象
        Class clazz = Class.forName("com.chanpller.springframework_6.chapter2.User");
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        user.add();
    }

    @Test
    public void testNewUserBySelf(){
        User user = new User();
        user.add();
    }
}
