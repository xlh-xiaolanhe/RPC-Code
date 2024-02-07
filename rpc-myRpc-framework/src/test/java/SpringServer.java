/**
 * @Package PACKAGE_NAME
 * @author xiaolanhe
 * @date 2024/2/7 11:18
 * @version V1.0
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 11:18
 */
public class SpringServer {
    public static void main(String[] args) throws IOException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-server.xml");

        System.in.read();
    }
}
