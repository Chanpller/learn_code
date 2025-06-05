import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisLettuceTest {
    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.builder()
                .withHost("192.168.197.136")
                .withPort(6379)
                .withAuthentication("default","redis")
                .build();
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> redisCommands = connect.sync();
        redisCommands.auth("redis");
        redisCommands.set("key1","value1");
        redisCommands.set("key2","value2");

        String key1 = redisCommands.get("key1");
        System.out.println(key1);

        String key2 = redisCommands.get("key2");
        System.out.println(key2);

        Map<String,String> map = new HashMap<>();
        map.put("age","1");
        map.put("name","zhangsan");
        redisCommands.hset("user",map);
        String hget = redisCommands.hget("user", "age");
        System.out.println(hget);
        String agee = redisCommands.hget("user", "agee");
        System.out.println(agee);

        redisCommands.lpush("list1","1","2","3","3");
        List<String> list1 = redisCommands.lrange("list1", 0, -1);
        System.out.println(list1);

        redisCommands.sadd("set1","1","2","3","3");
        Set<String> set1 = redisCommands.smembers("set1");
        System.out.println(set1);

        redisCommands.zadd("zset1",0.1,"1");
        redisCommands.zadd("zset1",0.2,"3");
        redisCommands.zadd("zset1",0.01,"2");
        List<String> zset1 = redisCommands.zrange("zset1",0,-1);
        System.out.println(zset1);

        connect.close();
        redisClient.shutdown();
    }
}
