package com.chanpller.mapper;

import com.chanpller.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> findAll();
}
