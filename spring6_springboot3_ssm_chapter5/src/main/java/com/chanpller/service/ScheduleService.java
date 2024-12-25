package com.chanpller.service;

import com.chanpller.pojo.Schedule;
import com.chanpller.utils.PageBean;

public interface ScheduleService {
    PageBean<Schedule> findByPage(int pageSize, int currentPage);

    void saveSchedule(Schedule schedule);

    void removeById(Integer id);

    void updateSchedule(Schedule schedule);
}
