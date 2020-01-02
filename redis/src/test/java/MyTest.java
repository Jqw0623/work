import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class MyTest {
    @Test
    public void test(){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
        UserService userService = context.getBean(UserService.class);
        String application = userService.getString("application");
        System.out.println(application);
    }
}
