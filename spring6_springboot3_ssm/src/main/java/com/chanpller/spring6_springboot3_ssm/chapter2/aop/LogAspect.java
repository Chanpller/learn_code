package com.chanpller.spring6_springboot3_ssm.chapter2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Before("execution(* com.chanpller.spring6_springboot3_ssm.chapter2.aop.EmployeeService.*(..))")
    public void test(JoinPoint joinPoint){
        System.out.println("日志");
    }
}
