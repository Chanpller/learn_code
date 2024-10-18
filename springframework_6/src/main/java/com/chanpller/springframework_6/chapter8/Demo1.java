package com.chanpller.springframework_6.chapter8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

public class Demo1 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext();
//        通过ApplicationContext访问资源
//        ApplicationContext实例获取Resource实例时，
//        默认采用与ApplicationContext相同的资源访问策略
        Resource res = ctx.getResource("atguigu.txt");
        System.out.println(res);

        res = ctx.getResource("calsspath:bean.xml");
        System.out.println(res);
        res = ctx.getResource("file:bean.xml");
        System.out.println(res);
        res = ctx.getResource("http://localhost:8080/beans.xml");
        System.out.println(res);

        ApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext();
        Resource fileSystemXmlApplicationContextResource = fileSystemXmlApplicationContext.getResource("atguigu.txt");
        System.out.println(fileSystemXmlApplicationContextResource);
    }
}
