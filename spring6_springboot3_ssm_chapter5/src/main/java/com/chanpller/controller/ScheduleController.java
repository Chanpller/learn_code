package com.chanpller.controller;

import com.chanpller.pojo.Schedule;
import com.chanpller.service.ScheduleService;
import com.chanpller.utils.PageBean;
import com.chanpller.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("schedule")
@RestController
public class ScheduleController
{

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{pageSize}/{currentPage}")
    public R showList(@PathVariable(name = "pageSize") int pageSize, @PathVariable(name = "currentPage") int currentPage){
        PageBean<Schedule> pageBean = scheduleService.findByPage(pageSize,currentPage);
        return  R.ok(pageBean);
    }

    @PostMapping
    public R saveSchedule(@RequestBody Schedule schedule){
        scheduleService.saveSchedule(schedule);
        return R.ok(null);
    }
    @DeleteMapping("/{id}")
    public R removeSchedule(@PathVariable Integer id){
        scheduleService.removeById(id);
        return R.ok(null);
    }
    @PutMapping
    public R changeSchedule(@RequestBody Schedule schedule){
        scheduleService.updateSchedule(schedule);
        return R.ok(null);
    }
}
