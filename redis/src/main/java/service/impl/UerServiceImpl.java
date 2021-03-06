package service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UerServiceImpl implements UserService {
    @Resource(name="redisTemplate")
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String getString(String key) {
        ValueOperations<String, String> string = redisTemplate.opsForValue();
        //【模拟操作】设置过期时间,TimeUnit.DAYS,以天作为单位
        redisTemplate.opsForValue().set("java1909","这是一个测试数据",2,TimeUnit.DAYS);
        //获取过期时间
        System.out.println(redisTemplate.getExpire("java1909", TimeUnit.MINUTES));

        //判断key是否存在
        if (redisTemplate.hasKey(key)) {
            System.out.println("redis读的值"+string.get(key));
            return string.get(key);
        } else {
            //模拟mysql数据库操作
            String result = "数据库的值";
            string.set(key,result);
            System.out.println("编写的值"+result);
            return result;
        }

    }
}
