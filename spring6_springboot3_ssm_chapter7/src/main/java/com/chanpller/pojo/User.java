package com.chanpller.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableLogic
//    @TableLogic(delval = "2",value = "1")
    //逻辑删除字段 int mybatis-plus下,默认 逻辑删除值为0 未逻辑删除 1
    private Integer deleted;
    @Version
    private Integer version;
}
