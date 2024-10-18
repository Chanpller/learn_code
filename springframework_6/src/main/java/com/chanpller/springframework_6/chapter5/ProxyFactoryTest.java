package com.chanpller.springframework_6.chapter5;

import org.junit.jupiter.api.Test;

public class ProxyFactoryTest {
    @Test
    public void testDynamicProxy(){
        ProxyFactory factory = new ProxyFactory(new CalculatorLogImpl());
        Calculator proxy = (Calculator) factory.getProxy();
//        proxy.div(1,0);
        proxy.div(1,1);
    }
}
