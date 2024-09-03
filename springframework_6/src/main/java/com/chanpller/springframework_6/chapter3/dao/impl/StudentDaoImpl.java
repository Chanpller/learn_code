package com.chanpller.springframework_6.chapter3.dao.impl;

import com.chanpller.springframework_6.chapter3.dao.UserDao;

public class StudentDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("调用了StudentDaoImpl实现类");
    }
}
