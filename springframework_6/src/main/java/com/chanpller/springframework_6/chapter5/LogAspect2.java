package com.chanpller.springframework_6.chapter5;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// @Aspect表示这个类是一个切面类
@Aspect
// @Component注解保证这个切面类能够放入IOC容器
@Component
public class LogAspect2 {
    @Before("com.chanpller.springframework_6.chapter5.LogAspect.pointCut()")
    public void beforeMethodPointCut(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("Logger-->重用切入点前置通知，方法名："+methodName+"，参数："+args);
    }
}
