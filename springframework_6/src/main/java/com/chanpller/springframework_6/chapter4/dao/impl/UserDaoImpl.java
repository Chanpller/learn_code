package com.chanpller.springframework_6.chapter4.dao.impl;

import com.chanpller.springframework_6.chapter4.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("调用了UserDaoImpl实现类");
    }
}