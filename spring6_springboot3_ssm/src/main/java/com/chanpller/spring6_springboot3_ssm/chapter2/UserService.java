package com.chanpller.spring6_springboot3_ssm.chapter2;

public class UserService {

    private UserDao userDao;

    private int age;

    private String name;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public UserService(int age , String name ,UserDao userDao) {
        this.userDao = userDao;
        this.age = age;
        this.name = name;
    }
}
