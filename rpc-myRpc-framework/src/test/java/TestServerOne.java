import com.xiaolanhe.provider.RpcServerProvider;
import com.xiaolanhe.registry.Registry;
import com.xiaolanhe.registry.ZookeeperRegistry;
import com.xiaolanhe.service.OrderService;
import com.xiaolanhe.service.UserService;
import com.xiaolanhe.service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 11:14
 */
public class TestServerOne {
    public static void main(String[] args) throws InterruptedException {
        Map<String, Object> exposed = new HashMap<>();
        exposed.put(UserService.class.getName(), new UserServiceImpl());
        exposed.put(OrderService.class.getName(), null);

        Registry zookeeperRegistry = new ZookeeperRegistry("192.168.235.132:2181");

        RpcServerProvider rpcServerProvider = new RpcServerProvider(8081, zookeeperRegistry, exposed);
        rpcServerProvider.startServer();
        System.out.println("server is started ... "+8081);
    }
}
