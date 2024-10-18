package com.chanpller.springframework_6.chapter5;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalculatorTest {
    private Logger logger = LoggerFactory.getLogger(CalculatorTest.class);

    @Test
    public void testAdd(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        Calculator calculator =  ac.getBean( Calculator.class);
        int add = calculator.add(1, 1);
        logger.info("执行成功:"+add);

        LogAspect logAspect =  ac.getBean( LogAspect.class);
        System.out.println(logAspect);
    }
}
