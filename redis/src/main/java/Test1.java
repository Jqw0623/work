import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pojo.Dog;
import pojo.User;
import redis.clients.jedis.Jedis;
import service.DogService;
import utils.RedisUtils;

import java.text.ParseException;
public class Test1 {
    Jedis myJedis = RedisUtils.getMyJedis();

    @Test
    public void TestDog3() throws ParseException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
        DogService dogService = context.getBean(DogService.class);
        //dogService.listAdd();
        //2,3
        long pageNo=0L;
        long pageSize=3L;
        dogService.listRange(pageNo,pageSize);
    }

    @Test
    public void TestDog2() throws ParseException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
        DogService dogService = context.getBean(DogService.class);
        dogService.add(new Dog("小白",4));
    }
    @Test
    public void TestDog() throws ParseException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
        DogService dogService = context.getBean(DogService.class);
        dogService.setString();
        dogService.getString();
    }

    //@Test
    //public void test1(){
    //    String host = "134.175.160.85";
    //    int port = 6379;
    //    Jedis j=new Jedis(host,port);
    //    j.auth("1234");//设置密码
    //    j.set("username","jack");
    //    String set = j.set("password", "1234");
    //    String password = j.get("password");
    //    String username = j.get("username");
    //    System.out.println(username);
    //    System.out.println(set);
    //    System.out.println(password);
    //}
    //@Test
    //public void test2(){
    //
    //    System.out.println(myJedis.get("username"));
    //}
    //
    ////hash (hashMap)
    //@Test
    //public void test3() {
    //    //模拟根据id查询一个用户
    //    Integer id=20;
    //    String key="user:"+id;//user:20，进行存储
    //    if(myJedis.exists(key)){
    //        //结果是Map<String, String>类型
    //        //获取key中的所有filed-vaule
    //        Map<String, String> user = myJedis.hgetAll(key);
    //        System.out.println(key+"--"+user.get("id")+"--"+user.get("username")+"--"+user.get("age"));
    //        //myJedis.del(key);
    //    }else{
    //        //为指定的key设定field/value对
    //        //myJedis.hset("users","id","1001");
    //        //myJedis.hset("users","username","jack");
    //        //myJedis.hset("users","age","12");
    //        HashMap<String, String> users = new HashMap<>();
    //        users.put("id","1001");
    //        users.put("username","rose");
    //        users.put("age","10");
    //        myJedis.hmset(key,users);
    //
    //    }
    //}
}
