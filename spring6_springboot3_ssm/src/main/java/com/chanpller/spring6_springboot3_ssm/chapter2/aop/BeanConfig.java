package com.chanpller.spring6_springboot3_ssm.chapter2.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.chanpller.spring6_springboot3_ssm.chapter2.aop")
@EnableAspectJAutoProxy
public class BeanConfig {
}
