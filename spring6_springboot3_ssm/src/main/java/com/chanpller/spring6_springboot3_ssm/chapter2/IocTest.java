package com.chanpller.spring6_springboot3_ssm.chapter2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTest {
    public static void main(String[] args) {
//        //方式1:实例化并且指定配置文件
////参数：String...locations 传入一个或者多个配置文件
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("services.xml", "daos.xml");
//
////方式2:先实例化，再指定配置文件，最后刷新容器触发Bean实例化动作 [springmvc源码和contextLoadListener源码方式]
//        ClassPathXmlApplicationContext iocContainer1 = new ClassPathXmlApplicationContext();
////设置配置配置文件,方法参数为可变参数,可以设置一个或者多个配置
//        iocContainer1.setConfigLocations("services.xml", "daos.xml");
////后配置的文件,需要调用refresh方法,触发刷新配置
//        iocContainer1.refresh();

        ClassPathXmlApplicationContext iocContainer =
                new ClassPathXmlApplicationContext("spring-ioc.xml");

        //方式1: 根据id获取
//没有指定类型,返回为Object,需要类型转化!
        HappyComponent happyComponent =
                (HappyComponent) iocContainer.getBean("happyComponent");

//使用组件对象
        happyComponent.doWork();

//方式2: 根据类型获取
//根据类型获取,但是要求,同类型(当前类,或者之类,或者接口的实现类)只能有一个对象交给IoC容器管理
//配置两个或者以上出现: org.springframework.beans.factory.NoUniqueBeanDefinitionException 问题
         happyComponent = iocContainer.getBean("happyComponent8",HappyComponent.class);
        happyComponent.doWork();

//方式3: 根据id和类型获取
         happyComponent = iocContainer.getBean("happyComponent", HappyComponent.class);
        happyComponent.doWork();

//        根据类型来获取bean时，在满足bean唯一性的前提下，其实只是/*看：『对象 instanceof 指定的类型』的返回结果，
//        只要返回的是true就可以认定为和类型匹配，能够获取到。


        HappyMachine bean = iocContainer.getBean("happyMachine8",HappyMachine.class);
        HappyMachine bean1 = iocContainer.getBean("happyMachine8",HappyMachine.class);
        //多例对比 false
        System.out.println(bean == bean1);

        HappyComponent bean2 = iocContainer.getBean("happyComponent8",HappyComponent.class);
        HappyComponent bean3 = iocContainer.getBean("happyComponent8",HappyComponent.class);
        //单例对比 true
        System.out.println(bean2 == bean3);


        //注意: 直接根据声明FactoryBean的id,获取的是getObject方法返回的对象
        HappyMachine happyMachine = iocContainer.getBean("happyMachine7",HappyMachine.class);
        System.out.println("happyMachine = " + happyMachine);

        //如果想要获取FactoryBean对象, 直接在id前添加&符号即可!  &happyMachine7 这是一种固定的约束
        Object beanHpoxy = iocContainer.getBean("&happyMachine7");
        System.out.println("bean = " + beanHpoxy);

        iocContainer.close();
    }
}
