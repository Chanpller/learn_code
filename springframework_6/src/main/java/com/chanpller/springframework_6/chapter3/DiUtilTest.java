package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter3.model.Collection;
import com.chanpller.springframework_6.chapter3.model.Dept;
import com.chanpller.springframework_6.chapter3.model.Emp;
import com.chanpller.springframework_6.chapter3.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class DiUtilTest {


    @Test
    public void test_SetterNameDi_list_map_util_Value(){
//
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean-util.xml");
        Collection collection = applicationContext.getBean("collection", Collection.class);
        System.out.println(Arrays.toString(collection.getHobi()));
        System.out.println(collection.getFreinds());
        System.out.println(collection.getStudents());
        //[抽烟, 喝酒, 烫头]
        //[Student{name='张三', age=24, other='other'}, Student{name='李四', age=22, other='other'}, Student{name='王五', age=30, other='other'}]
        //{1001=Student{name='null', age=0, other='a < b'}, 1002=Student{name='null', age=0, other='a < b'}}
    }
}
