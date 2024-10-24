package com.chanpller.spring6_springboot3_ssm.chapter2;

public class BeanTwo {
    public BeanTwo() {
        System.out.println("BeanTwo调用构造方法了");
    }
    //周期方法要求： 方法命名随意，但是要求方法必须是 public void 无形参列表
    public void init() {
        // 初始化逻辑
        System.out.println("BeanTwo初始化了");
    }
    public void cleanup() {
        // 释放资源逻辑
        System.out.println("BeanTwo销毁了");
    }
}