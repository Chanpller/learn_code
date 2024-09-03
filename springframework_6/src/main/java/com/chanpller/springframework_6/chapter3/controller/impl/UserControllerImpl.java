package com.chanpller.springframework_6.chapter3.controller.impl;

import com.chanpller.springframework_6.chapter3.controller.UserController;
import com.chanpller.springframework_6.chapter3.service.UserService;

public class UserControllerImpl implements UserController {
    private UserService userService;
    @Override
    public void save() {
        System.out.println("调用了UserControllerImpl实现类");
        userService.save();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
