package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter3.model.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {
    @Test
    public void test_singleton() {
//
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean-scop.xml");
        Dept dept1 = applicationContext.getBean("dept_singleton", Dept.class);
        System.out.println(dept1);
        Dept dept2 = applicationContext.getBean("dept_singleton", Dept.class);
        System.out.println(dept2);
        Dept dept3 = applicationContext.getBean("dept_singleton", Dept.class);
        System.out.println(dept3);
//        com.chanpller.springframework_6.chapter3.model.Dept@142eef62
//        com.chanpller.springframework_6.chapter3.model.Dept@142eef62
//        com.chanpller.springframework_6.chapter3.model.Dept@142eef62
    }
    @Test
    public void test_prototype() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean-scop.xml");
        Dept dept1 = applicationContext.getBean("dept_prototype", Dept.class);
        System.out.println(dept1);
        Dept dept2 = applicationContext.getBean("dept_prototype", Dept.class);
        System.out.println(dept2);
        Dept dept3 = applicationContext.getBean("dept_prototype", Dept.class);
        System.out.println(dept3);
//com.chanpller.springframework_6.chapter3.model.Dept@4a9cc6cb
//com.chanpller.springframework_6.chapter3.model.Dept@5990e6c5
//com.chanpller.springframework_6.chapter3.model.Dept@56e07a08
    }
}
