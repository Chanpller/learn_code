import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.197.136",6379);
        jedis.auth("redis");
        jedis.set("key1","value1");
        jedis.set("key2","value2");

        String key1 = jedis.get("key1");
        System.out.println(key1);

        String key2 = jedis.get("key2");
        System.out.println(key2);

        Map<String,String> map = new HashMap<>();
        map.put("age","1");
        map.put("name","zhangsan");
        jedis.hset("user",map);
        String hget = jedis.hget("user", "age");
        System.out.println(hget);
        String agee = jedis.hget("user", "agee");
        System.out.println(agee);

        jedis.lpush("list1","1","2","3","3");
        List<String> list1 = jedis.lrange("list1", 0, -1);
        System.out.println(list1);

        jedis.sadd("set1","1","2","3","3");
        Set<String> set1 = jedis.smembers("set1");
        System.out.println(set1);

        jedis.zadd("zset1",0.1,"1");
        jedis.zadd("zset1",0.2,"3");
        jedis.zadd("zset1",0.01,"2");
        List<String> zset1 = jedis.zrange("zset1",0,-1);
        System.out.println(zset1);
    }
}
