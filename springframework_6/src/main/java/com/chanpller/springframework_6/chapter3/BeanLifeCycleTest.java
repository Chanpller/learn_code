package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter3.model.Dept;
import com.chanpller.springframework_6.chapter3.model.LifeCycleBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleTest {
    @Test
    public void test_SetterNameDi_File_Value() {
//
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean-lifecycle.xml");
        LifeCycleBean lifeCycleBean = applicationContext.getBean("lifeCycleBean", LifeCycleBean.class);
        System.out.println("6 对象创建了，可以使用了"+lifeCycleBean);
        applicationContext.close();
//        1 对象创建了
//        2 属性被设置了
//        3 前置方法被调用，beanName=lifeCycleBean,bean=com.chanpller.springframework_6.chapter3.model.LifeCycleBean@1fc0053e
//        4 初始化了
//        5 后置方法被调用，beanName=lifeCycleBean,bean=com.chanpller.springframework_6.chapter3.model.LifeCycleBean@1fc0053e
//        6 对象创建了，可以使用了com.chanpller.springframework_6.chapter3.model.LifeCycleBean@1fc0053e
//        2024-08-29 23:21:28 703 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Closing org.springframework.context.support.ClassPathXmlApplicationContext@2d6764b2, started on Thu Aug 29 23:21:28 CST 2024
//        7 对象被销毁了
    }
}
