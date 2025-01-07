package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.model.User;
import com.test.service.UserService;
import com.test.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author cp
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-01-07 12:02:42
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




