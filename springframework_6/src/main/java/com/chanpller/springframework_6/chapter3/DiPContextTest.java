package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter3.model.Dept;
import com.chanpller.springframework_6.chapter3.model.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiPContextTest {


    @Test
    public void test_SetterNameDi_File_Value() {
//
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean-context.xml");
        Dept dept = applicationContext.getBean("dept", Dept.class);
        dept.info();
        //部门是。。。。??????_???
    }
}
