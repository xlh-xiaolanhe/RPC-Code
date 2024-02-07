import com.xiaolanhe.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 11:18
 */
public class SpringClient {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-client.xml");

        /** 当你调用applicationContext.getBean(UserService.class)时，Spring容器会理解为请求一个可以分配给UserService类型的对象。
        由于userService定义中的ProxyFactoryBean生成了UserService的代理对象，所以这个代理对象被返回。*/

        UserService userService = applicationContext.getBean(UserService.class);

        /** 当代理对象的login方法被调用时，Java的代理机制会自动调用InvocationHandler实现的invoke方法。*/

        boolean ret = userService.login("xiaobai", "101010");
        System.out.println("ret = " + ret);
    }
}
