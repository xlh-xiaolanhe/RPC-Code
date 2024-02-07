import com.xiaolanhe.provider.RpcServerProvider;
import com.xiaolanhe.registry.Registry;
import com.xiaolanhe.registry.ZookeeperRegistry;
import com.xiaolanhe.service.OrderService;
import com.xiaolanhe.service.UserService;
import com.xiaolanhe.service.UserServiceImpl;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 11:17
 */
public class TestServerTwo {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        Map<String, Object> exposeBeans = new HashMap<>();
        exposeBeans.put(UserService.class.getName(), new UserServiceImpl());
        exposeBeans.put(OrderService.class.getName(), null);

        Registry registry = new ZookeeperRegistry("192.168.235.132:2181");

        RpcServerProvider rpcServerProvider = new RpcServerProvider(8082, registry, exposeBeans);
        rpcServerProvider.startServer();
        System.out.println("server is started ... "+8082);

    }
}
