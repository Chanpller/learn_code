package com.chanpller.springframework_6.chapter7.service;

import com.chanpller.springframework_6.chapter7.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    //只读属性，对增删改操作设置只读会抛出下面异常：Caused by: java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed
//    @Transactional(readOnly = true)
    //超时时间单位秒
//    @Transactional(timeout = 3)

    @Transactional(noRollbackFor = DataIntegrityViolationException.class)
//@Transactional(noRollbackForClassName = "java.lang.ArithmeticException")
    public void buyBook(Integer bookId, Integer userId) {
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //查询图书的价格
        Integer price = bookDao.getPriceByBookId(bookId);
        //更新图书的库存
        bookDao.updateStock(bookId);
        //更新用户的余额
        bookDao.updateBalance(userId, price);
    }
}