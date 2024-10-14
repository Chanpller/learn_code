package com.chanpller.springframework_6.chapter4.service.impl;

import com.chanpller.springframework_6.chapter4.dao.UserDao;
import com.chanpller.springframework_6.chapter4.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Override
    public void save() {
        System.out.println("调用了UserServiceImpl实现类");
        userDao.save();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}