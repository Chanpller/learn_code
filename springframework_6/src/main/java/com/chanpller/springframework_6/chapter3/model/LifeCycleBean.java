package com.chanpller.springframework_6.chapter3.model;

public class LifeCycleBean {
    private String name;

    public LifeCycleBean() {
        System.out.println("1 对象创建了");
    }

    public void setName(String name) {
        System.out.println("2 属性被设置了");
        this.name = name;
    }
    public void initMethod(){
        System.out.println("4 初始化了");
    }
    public void  destoryMethod(){
        System.out.println("7 对象被销毁了");
    }
}
