package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import pojo.Dog;
import service.DogService;
import utils.RedisUtil;
import utils.RedisUtilCopy;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("dogService")
public class DogServiceImpl implements DogService {
    @Resource(name="redisUtil")
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Resource(name="redisTemplate")
    ValueOperations<String,String> vlaue;//redisTemplate.opsForValue()

    //@Autowired
    //private RedisUtilCopy redisUtilCopy;
    @Resource(name="redisTemplate")
    private HashOperations<String,Object,Dog> hash;// redisTemplate.opsForHash()
    @Resource(name="redisTemplate")
    private ListOperations<String,String> list;//redisTemplate.opsForList()
    @Override
    public void setString() {
        Dog dog = new Dog("旺财", 12);
        redisUtil.setEx("key1", dog.toString(),2, TimeUnit.DAYS);
        //HH24
        try {
            redisUtil.set("k2","123");
            redisUtil.expireAt("k2",new Date(new SimpleDateFormat("yyyy-" +
                    "MM-dd HH:mm:ss").parse("2020-1-3 22:00:00").getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getString() {

        System.out.println("redisUtil:"+redisUtil.getExpire("key1",TimeUnit.HOURS));
        System.out.println(redisUtil.getExpire("key2",TimeUnit.HOURS));
    }

    @Override
    public void add(Dog dog) {
        //key-hashkey-hashvalue
        redisTemplate.opsForHash().put("dog1",dog.getName(),dog);
        //key-hashkey
        if(redisTemplate.opsForHash().hasKey("dog1",dog.getName())){
            //key-hashkey
            System.out.println(redisTemplate.opsForHash().get("dog1",dog.getName()));
        }
        System.out.println(redisTemplate.opsForHash().entries("dog1"));
        //redisUtil.hPut("dog2",dog.getName(),dog.getName());
        //if(redisUtil.hExists("dog2",dog.getName())){
        //    System.out.println(redisUtil.hGet("dog2",dog.getName()));
        //}
    }

    //list

    @Override
    public void listAdd() {
        String key="news:top10";
        //list.leftPush(key,"0");
        //list.leftPushAll(key,"1","2","3","哈哈","哇哇");
        list.rightPushAll(key,"4","5","6","7","pp","oo");
    }

    @Override
    public Object listRange(long pageNo,long pageSize) {
        String key="news:top10";
        //分页
        long start=(pageNo-1)*pageSize;
        long stop=pageNo*pageSize-1;
        List<String> stringList = list.range(key,start,stop);//list集合
        System.out.println(stringList);

        Long size = list.size(key);
        System.out.println("总记录数："+size);

        return null;
    }


}
