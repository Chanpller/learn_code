package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter3.model.Emp;
import com.chanpller.springframework_6.chapter3.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiTest {
    @Test
    public void test_SetterNameDi(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student.toString());
    }
    @Test
    public void test_ConstructorNameDi(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        Student student = applicationContext.getBean("student1", Student.class);
        System.out.println(student.toString());
    }
    @Test
    public void test_ConstructorIndexDi(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        Student student = applicationContext.getBean("student2", Student.class);
        System.out.println(student.toString());
    }

    @Test
    public void test_SetterNameDi_xml_Value(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        Student student = applicationContext.getBean("student_xml", Student.class);
        System.out.println(student.toString());
        //Student{name='null', age=0, other='a < b'}
    }
    @Test
    public void test_SetterNameDi_cdata_Value(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        Student student = applicationContext.getBean("student_cdata", Student.class);
        System.out.println(student.toString());
        //Student{name='null', age=0, other='a < b'}
    }

    @Test
    public void test_SetterNameDi_out_ref_Value(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        Emp emp = applicationContext.getBean("emp", Emp.class);
        emp.work();
        //员工信息：name:张三,age:20
        //部门是。。。。工程部
    }
}
