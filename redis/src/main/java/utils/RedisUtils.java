package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
    private static JedisPool pool;
    static {
        //1，连接池配置
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(5);
        jedisPoolConfig.setMaxIdle(1);
        //其他配置
        //2，连接池
        pool=new JedisPool(jedisPoolConfig,"134.175.160.85",6379);
    }

    public static Jedis getMyJedis(){
        Jedis jedis = pool.getResource();
        jedis.auth("1234");
        return jedis;
    }

    public static void close(Jedis jedis){
        jedis.close();
    }
}
