import com.xiaolanhe.cluster.FailOverCluster;
import com.xiaolanhe.loadbalance.RandomLoadBalancer;
import com.xiaolanhe.proxy.JDKProxy;
import com.xiaolanhe.registry.ZookeeperRegistry;
import com.xiaolanhe.service.UserService;
import com.xiaolanhe.transport.NettyTransport;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 11:16
 */

/**-Dio.netty.tryReflectionSetAccessible=true
 --add-opens
 java.base/java.lang=ALL-UNNAMED
 --add-opens
 java.base/java.lang.reflect=ALL-UNNAMED
 --add-opens
 java.base/jdk.internal.misc=ALL-UNNAMED
 --add-opens
 java.base/java.util=ALL-UNNAMED
 --add-opens
 java.base/java.nio=ALL-UNNAMED*/

public class TestClientTwo {
    public static void main(String[] args) {
        JDKProxy jdkProxy = new JDKProxy(UserService.class);

        jdkProxy.setRegistry(new ZookeeperRegistry("192.168.235.132:2181"));
        jdkProxy.setCluster(new FailOverCluster());
        jdkProxy.setTransport(new NettyTransport());
        jdkProxy.setLoadBalancer(new RandomLoadBalancer());

        UserService userService = (UserService) jdkProxy.createProxy();
        System.out.println(userService + "123 ");


        boolean result = userService.login("xiaolanhe", "999999");

        System.out.println("result = " + result);
    }
}
