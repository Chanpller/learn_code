package com.chanpller.springframework_6.chapter3.model;

public class Dept {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void info(){
        System.out.println("部门是。。。。"+name);
    }
}
