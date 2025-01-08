package com.chanpller.service;

import com.chanpller.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chanpller.vo.Result;

/**
* @author Administrator
* @description 针对表【news_user】的数据库操作Service
* @createDate 2025-01-08 10:19:09
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);
}
