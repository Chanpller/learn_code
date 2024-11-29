package com.chanpller.spring6_springboot3_ssm.chapter3.base.test;

import com.chanpller.spring6_springboot3_ssm.chapter3.base.dao.EmployeeMapper;
import com.chanpller.spring6_springboot3_ssm.chapter3.base.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyBatisTest {
    SqlSession sqlSession;
    @BeforeEach
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("base/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();

    }
    @AfterEach
    public void after() throws IOException {
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void testSelectEmployee() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("base/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectEmployee(1);
        System.out.println(employee);

        //sqlSession提供了curd方法进行数据库查询
        //selectOne/selectList/insert/delete/update 这些sqlSession提供的方法，是通过找xml中对应的sql语句标签，然后执行。
        //参数1：字符串 对应xml文件中的sql标签id或则namespace.id。参数2：Object 执行sql语句传入的参数
        Object o = sqlSession.selectOne("ibatiesTestSelectOne", 2);
        System.out.println(o);
        Object o1 = sqlSession.selectOne("ibatiesTestSelectOneValue", 2);
        System.out.println(o1);
        // 关闭SqlSession
        sqlSession.commit(); //提交事务 [DQL不需要,其他需要]
        sqlSession.close(); //关闭会话
    }

    @Test
    public void testInsert() throws IOException {
        init();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee();
        employee.setEmpName("lili");
        employee.setEmpSalary(200.0);
        int i = mapper.insertEmp(employee);
        System.out.println("插入数据"+i);
        after();
    }
    @Test
    public void testUpdate() throws IOException {
        init();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        int i = mapper.updateEmployeeArg(1,500.0);
        System.out.println("更新数据"+i);
        i = mapper.updateEmployeeAnnationParam(1,500.0);
        System.out.println("更新数据"+i);
        i = mapper.updateEmployeeParam(1,500.0);
        System.out.println("更新数据"+i);
        after();
    }
    @Test
    public void testUpadteByMap(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empSalaryKey", 999.99);
        paramMap.put("empIdKey", 1);
        int result = mapper.updateEmployeeByMap(paramMap);
        System.out.println("更新数据"+result);
    }

    @Test
    public void testEmpCount() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        int count = employeeMapper.selectEmpCount();
        System.out.println("count="+count);
    }

    @Test
    public void selectEmployeeAliases() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.selectEmployeeAliases(1);
        System.out.println("employee="+employee);
    }

    @Test
    public void testQueryEmpNameAndSalary() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Map<String, Object> resultMap = employeeMapper.selectEmpNameAndMaxSalary();
        Set<Map.Entry<String, Object>> entrySet = resultMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "=" + value);
        }
    }

    @Test
    public void testSelectAll() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employeeList = employeeMapper.selectAll();
        for (Employee employee : employeeList) {
            System.out.println("employee = " + employee);
        }
    }

    @Test
    public void testSaveEmp() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee();
        employee.setEmpName("john");
        employee.setEmpSalary(666.66);
        employeeMapper.insertEmployee(employee);
        System.out.println("employee.getEmpId() = " + employee.getEmpId());
    }
}
