package com.chanpller.controller;

import com.chanpller.mapper.ScheduleMapper;
import com.chanpller.pojo.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping("schedule")
@RestController
public class ScheduleController
{

    @Autowired
    private ScheduleMapper scheduleMapper;

    @GetMapping()
    public List<Schedule> queryAll(){
        return  scheduleMapper.queryAll();
    }

}
