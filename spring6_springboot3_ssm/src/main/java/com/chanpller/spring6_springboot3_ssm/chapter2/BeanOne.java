package com.chanpller.spring6_springboot3_ssm.chapter2;

public class BeanOne {

    public BeanOne() {
        System.out.println("BeanOne调用构造方法了");
    }

    //周期方法要求： 方法命名随意，但是要求方法必须是 public void 无形参列表
    public void init() {
        // 初始化逻辑
        System.out.println("BeanOne初始化了");
    }
}
