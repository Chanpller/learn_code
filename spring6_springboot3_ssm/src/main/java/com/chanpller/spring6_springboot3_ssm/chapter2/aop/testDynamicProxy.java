package com.chanpller.spring6_springboot3_ssm.chapter2.aop;

import org.junit.jupiter.api.Test;

public class testDynamicProxy {
    @Test
    public void testDynamicProxy(){
        ProxyFactory factory = new ProxyFactory(new CalculatorLogImpl());
        Calculator proxy = (Calculator) factory.getProxy();
        proxy.div(1,1);
        //proxy.div(1,1);
    }
}
