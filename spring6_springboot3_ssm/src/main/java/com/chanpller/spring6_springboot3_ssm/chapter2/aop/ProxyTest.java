package com.chanpller.spring6_springboot3_ssm.chapter2.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(value = {BeanConfig.class})  //指定配置类
public class ProxyTest {
    @Autowired
    private EmployeeServiceInterface employeeServiceInterface;

    @Test
    public void testNoInterfaceProxy() {
        employeeServiceInterface.getEmpList();
        System.out.println(employeeServiceInterface);
    }
}
