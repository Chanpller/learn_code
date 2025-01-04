package com.chanpller.controller;

import com.chanpller.pojo.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/getUser")
    @ResponseBody
    public Schedule getUser(){
        String sql = "select * from schedule where id = ? ; ";
        Schedule schedule = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Schedule.class), 2);
        log.info("查询的user数据为:{}",schedule.toString());
        return schedule;
    }

}