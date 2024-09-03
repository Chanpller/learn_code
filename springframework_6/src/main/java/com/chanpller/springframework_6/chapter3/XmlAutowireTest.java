package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter3.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlAutowireTest {
    @Test
   public void test_XmlAutowire(){
       ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean-xml-autowire.xml");
       UserController userController = applicationContext.getBean("userController",UserController.class);
       userController.save();
       //调用了UserControllerImpl实现类
       //调用了UserServiceImpl实现类
        // 调用了UserDaoImpl实现类
   }
}
