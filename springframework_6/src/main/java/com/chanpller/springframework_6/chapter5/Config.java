package com.chanpller.springframework_6.chapter5;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
@EnableAspectJAutoProxy
public class Config {
}
