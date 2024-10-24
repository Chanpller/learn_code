package com.chanpller.spring6_springboot3_ssm.chapter2.aop;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements EmployeeServiceInterface{

    public void getEmpList() {
        System.out.print("方法内部 com.atguigu.aop.imp.EmployeeService.getEmpList");
    }
}
