package com.chanpller.springframework_6.chapter4.service.impl;

import com.chanpller.springframework_6.chapter4.annotion.Bean;
import com.chanpller.springframework_6.chapter4.annotion.Di;
import com.chanpller.springframework_6.chapter4.dao.UserDao;
import com.chanpller.springframework_6.chapter4.service.UserService;

@Bean
public class UserServiceImpl implements UserService {
    @Di
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
