package com.chanpller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chanpller.mapper.UserMapper;
import com.chanpller.pojo.User;
import com.chanpller.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest //springboot下测试环境注解
public class SampleTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);

        User user = new User();
        user.setId(1L);
        user.setAge(30);

        userMapper.updateById(user);
//        userMapper.updateById(null);
//        userMapper.update(user,null);
        userMapper.update(null,new UpdateWrapper<User>().set("age",Long.valueOf(40l)));
    }
    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);
        //获取分页数据
        List<User> list = page.getRecords();
        System.out.printf(Arrays.toString(list.toArray()));
    }
    @Test
    public void testPageBySelf() {
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPageVo(page, Integer.valueOf(1));
        //获取分页数据
        List<User> list = page.getRecords();
        System.out.printf(Arrays.toString(list.toArray()));

         Page<User> page1 = userService.page(page);
        System.out.printf(Arrays.toString(page1.getRecords().toArray()));
    }
    @Test
    public void test01(){
        //查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02(){
        //按年龄降序查询用户，如果年龄相同则按id升序排列
        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("age")
                .orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test03(){
        //删除email为空的用户
        //DELETE FROM t_user WHERE (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        //条件构造器也可以构建删除语句的条件
        int result = userMapper.delete(queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test04() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //将年龄大于20并且用户名中包含有a或邮箱为null的用户信息修改
        //UPDATE t_user SET age=?, email=? WHERE username LIKE ? AND age > ? OR email IS NULL)
        queryWrapper
                .like("name", "a")
                .gt("age", 20)
                .or()
                .isNull("email");
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test05() {
        //查询用户信息的username和age字段
        //SELECT username,age FROM t_user
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age");
        //selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到的列值为null
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void testQuick3(){

        String name = "root";
        int    age = 18;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //判断条件拼接
        //当name不为null拼接等于, age > 1 拼接等于判断
        //方案1: 手动判断
        if (!StringUtils.isEmpty(name)){
            queryWrapper.eq("name",name);
        }
        if (age > 1){
            queryWrapper.eq("age",age);
        }

        //方案2: 拼接condition判断
        //每个条件拼接方法都condition参数,这是一个比较运算,为true追加当前条件!
        //eq(condition,列名,值)
        queryWrapper.eq(!StringUtils.isEmpty(name),"name",name)
                .eq(age>1,"age",age);
    }

    @Test
    public void testQuick2(){

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        //将id = 3 的email设置为null, age = 18
        updateWrapper.eq("id",3)
                .set("email",null)  // set 指定列和结果
                .set("age",18);
        //如果使用updateWrapper 实体对象写null即可!
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result = " + result);

    }

    @Test
    public void testQuick4(){

        String name = "root";
        int    age = 18;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //每个条件拼接方法都condition参数,这是一个比较运算,为true追加当前条件!
        //eq(condition,列名,值)
        queryWrapper.eq(!StringUtils.isEmpty(name),"name",name)
                .eq(age>1,"age",age);

        //TODO: 使用lambdaQueryWrapper
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //注意: 需要使用方法引用
        //技巧: 类名::方法名
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(name), User::getName,name);
        List<User> users= userMapper.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }
    @Test
    public void testQuick5(){

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        //将id = 3 的email设置为null, age = 18
        updateWrapper.eq("id",3)
                .set("email",null)  // set 指定列和结果
                .set("age",18);

        //使用lambdaUpdateWrapper
        LambdaUpdateWrapper<User> updateWrapper1 = new LambdaUpdateWrapper<>();
        updateWrapper1.eq(User::getId,3)
                .set(User::getEmail,null)
                .set(User::getAge,18);

        //如果使用updateWrapper 实体对象写null即可!
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testQuick6(){

        User user = userMapper.selectById(1);
        System.out.println(user);
    }
    @Test
    public void testInsertId(){

        User user = new User();
        user.setName("测试逐渐");
        user.setAge(10);
        user.setEmail("test");
                userMapper.insert(user);
        System.out.println(user);
    }

    @Test
    public void testLogicDelete(){

        userMapper.deleteById(1);
    }

    //演示乐观锁生效场景
    @Test
    public void testQuick7(){
        //步骤1: 先查询,在更新 获取version数据
        //同时查询两条,但是version唯一,最后更新的失败
        User user  = userMapper.selectById(4);
        User user1  = userMapper.selectById(4);

        user.setAge(20);
        user1.setAge(30);

        int i = userMapper.updateById(user);
        System.out.println(i);
        System.out.println(user);
        //乐观锁生效,失败!
        int i1 = userMapper.updateById(user);
        System.out.println(i1);
        System.out.println(user1);

        //再更新user是可以成功的
        userMapper.updateById(user);
    }

    @Test
    public void testQuick8(){
        User user = new User();
        user.setName("custom_name");
        user.setEmail("xxx@mail.com");
        //Caused by: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of table update operation
        //全局更新,报错
        userService.saveOrUpdate(user,null);
    }
}