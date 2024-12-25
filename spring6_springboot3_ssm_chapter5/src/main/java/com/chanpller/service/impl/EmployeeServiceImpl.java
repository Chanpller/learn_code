package com.chanpller.service.impl;

import com.chanpller.mapper.EmployeeMapper;
import com.chanpller.pojo.Employee;
import com.chanpller.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }
}
