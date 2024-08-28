package com.chanpller.springframework_6.chapter3.model;

public class Emp {
    private String name;
    private int age;
    private Dept dept;
    public void work(){
        System.out.println("员工信息：name:"+name+",age:"+age);
        dept.info();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dept=" + dept +
                '}';
    }
}
