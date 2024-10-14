package com.chanpller.springframework_6.chapter3.annotation.impl;

import com.chanpller.springframework_6.chapter3.annotation.UserService;
import com.chanpller.springframework_6.chapter3.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("调用了UserServiceImpl实现类");
    }
}
