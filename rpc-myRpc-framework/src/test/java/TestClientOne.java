import com.xiaolanhe.protocol.MethodInvokeData;
import com.xiaolanhe.protocol.Result;
import com.xiaolanhe.service.UserService;
import com.xiaolanhe.transport.NettyTransport;
import com.xiaolanhe.transport.Transport;
import com.xiaolanhe.common.HostAndPort;

import java.net.InetAddress;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 11:15
 */

// need to add vm options: --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED
public class TestClientOne {
    public static void main(String[] args) throws Exception {
        Transport transport = new NettyTransport();

        HostAndPort hostAndPort = new HostAndPort(InetAddress.getLocalHost().getHostName(), 8081);

        MethodInvokeData methodInvokeData = new MethodInvokeData(UserService.class, "login", new Class[]{String.class, String.class}, new Object[]{"xiaolanhe", "213456"});

        Result result = transport.invoke(hostAndPort, methodInvokeData);

        if(null != result.getException()){
            throw result.getException();
        }

        System.out.println("result = " + result.getResultValue());
    }
}
