package com.chanpller.mapper;

import com.chanpller.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<Schedule> queryPage();

    void insert(Schedule schedule);

    void delete(Integer id);

    void update(Schedule schedule);
}
