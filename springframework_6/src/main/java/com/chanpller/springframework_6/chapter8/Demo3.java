package com.chanpller.springframework_6.chapter8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class Demo3 {

    public static void main(String[] args) {
        //Spring容器会将一个ResourceLoader对象作为该方法的参数传入
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-resource.xml");
        TestBean testBean = ctx.getBean("testBean",TestBean.class);
        //获取ResourceLoader对象
        ResourceLoader resourceLoader = testBean.getResourceLoader();
        System.out.println("Spring容器将自身注入到ResourceLoaderAware Bean 中 ？ ：" + (resourceLoader == ctx));
        //加载其他资源
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());

        //classpath*前缀不生效
        resource = resourceLoader.getResource("classpath*:test.txt");
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());

        //classpath后面的*也不生效
        resource = resourceLoader.getResource("classpath:test*.txt");
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
    }
}