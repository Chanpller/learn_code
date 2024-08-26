package com.chanpller.springframework_6.chapter2;

public class User {
    public User() {
        System.out.println("User 无参构造器");
    }

    private String name;
    private int age;
    public void add(){
        System.out.println("User add....");
    }
}
