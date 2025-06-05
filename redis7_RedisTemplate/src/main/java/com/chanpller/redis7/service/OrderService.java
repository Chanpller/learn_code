package com.chanpller.redis7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {
    public static  final String ORDER_KEY = "ord";

    @Autowired
    private RedisTemplate redisTemplate;
    public void addOrder() {
        int keyId  = ThreadLocalRandom.current().nextInt(1000)+1;
        String serialNo = UUID.randomUUID().toString();
        String key = ORDER_KEY+keyId;
        String value = "京东订单"+serialNo;
        System.out.println(key);
        redisTemplate.opsForValue().set(key,value);
    }

    public String getOrderById(Integer keyId) {
        return (String)redisTemplate.opsForValue().get(ORDER_KEY+keyId);
    }
}
