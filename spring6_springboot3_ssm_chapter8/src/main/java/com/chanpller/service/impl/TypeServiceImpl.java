package com.chanpller.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chanpller.model.Type;
import com.chanpller.service.TypeService;
import com.chanpller.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2025-01-08 10:19:09
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




