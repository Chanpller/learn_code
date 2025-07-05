package com.chanpller.com.utis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Random;

public class RedisUtils {
    public static final String REDIS_IP_ADDR = "192.168.197.128";

    public static final String REDIS_PWD = "redis";

    public static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(200);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig, REDIS_IP_ADDR, 6379, 10000, REDIS_PWD);
    }

    public static Jedis getJedis() throws Exception {
        if (null != jedisPool) {
            return jedisPool.getResource();
        }
        throw new Exception("Jedispoll is not ok");
    }
    public static  void initIp(){
        new Thread(()->{
            String ip = null;
            Random random = new Random();
            for (int i=0;i<200;i++){
                ip = random.nextInt(256)+"."+
                        random.nextInt(256)+"."+
                        random.nextInt(256)+"."+
                        random.nextInt(256)+".";
                try {
                    Jedis jedis = getJedis();
                    Long h11 = jedis.pfadd("h12", ip);
                    System.out.println("ip="+ip+",该ip地址访问首页的次数="+h11);
                    jedis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Jedis jedis = getJedis();
                System.out.println(getJedis().pfcount("h12"));
                jedis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    public static void main(String[] args) {
        initIp();
    }
}
