package com.chanpller.spring6_springboot3_ssm.chapter3.base.dao;

import com.chanpller.spring6_springboot3_ssm.chapter3.base.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    Employee selectEmployee(Integer empId);

    int insertEmp(Employee employee);

    int updateEmployeeArg(Integer empId, Double empSalary);
    int updateEmployeeAnnationParam(@Param("empId") Integer empId, @Param("empSalary") Double empSalary);
    int updateEmployeeParam(Integer empId, Double empSalary);

    int updateEmployeeByMap(Map<String, Object> paramMap);

    int selectEmpCount();

    Employee selectEmployeeAliases(Integer empId);

    Map<String,Object> selectEmpNameAndMaxSalary();

    List<Employee> selectAll();

    int insertEmployee(Employee employee);
}
