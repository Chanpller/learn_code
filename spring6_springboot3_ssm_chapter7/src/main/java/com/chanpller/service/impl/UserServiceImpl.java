package com.chanpller.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chanpller.mapper.UserMapper;
import com.chanpller.pojo.User;
import com.chanpller.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
