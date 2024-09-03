package com.chanpller.springframework_6.chapter3;

import com.chanpller.springframework_6.chapter3.model.Collection;
import com.chanpller.springframework_6.chapter3.model.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class DiPTest {


    @Test
    public void test_SetterNameDi_list_map_util_Value() {
//
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean-p.xml");
        Emp emp = applicationContext.getBean("emp_p", Emp.class);
        emp.work();
        //员工信息：name:p_张三,age:20
        //部门是。。。。工程部
    }
}
