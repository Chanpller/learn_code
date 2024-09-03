package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter3.model.Collection;
import com.chanpller.springframework_6.chapter3.model.Dept;
import com.chanpller.springframework_6.chapter3.model.Emp;
import com.chanpller.springframework_6.chapter3.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

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
    @Test
    public void test_SetterNameDi_inner_ref_Value(){
//        * 内部注入的bean的id与外部标签的bean不冲突，可以同名
//        * 内部注入的bean通过ApplicationContext获取不到
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        Emp emp = applicationContext.getBean("emp_inner", Emp.class);
        emp.work();
        Dept dept = applicationContext.getBean("dep_inner", Dept.class);
        dept.info();
        //员工信息：name:内部注入_张三,age:20
        //部门是。。。。内部注入部门
        //dep_inner获取不到，报错
    }

    @Test
    public void test_SetterNameDi_cascade_Value(){
//        级联属性注入，需要先将级联的对象引入，如果先引入属性，则报BeanCreationException异常
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        Emp emp = applicationContext.getBean("emp_cascade", Emp.class);
        emp.work();
        //员工信息：name:级联_张三,age:20
        //部门是。。。。级联_部门
    }

    @Test
    public void test_SetterNameDi_list_map_array_Value(){
//
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean.xml");
        Collection collection = applicationContext.getBean("collection", Collection.class);
        System.out.println(Arrays.toString(collection.getHobi()));
        System.out.println(collection.getFreinds());
        System.out.println(collection.getStudents());
        //[抽烟, 喝酒, 烫头]
        //[Student{name='张三', age=24, other='other'}, Student{name='李四', age=22, other='other'}, Student{name='王五', age=30, other='other'}]
        //{1001=Student{name='null', age=0, other='a < b'}, 1002=Student{name='null', age=0, other='a < b'}}
    }
}
