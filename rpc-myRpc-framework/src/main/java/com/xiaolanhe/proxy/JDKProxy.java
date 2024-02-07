package com.xiaolanhe.proxy;

import com.xiaolanhe.cluster.Cluster;
import com.xiaolanhe.common.HostAndPort;
import com.xiaolanhe.loadbalance.LoadBalancer;
import com.xiaolanhe.protocol.Result;
import lombok.Data;
import com.xiaolanhe.registry.Registry;
import com.xiaolanhe.transport.Transport;
import com.xiaolanhe.protocol.MethodInvokeData;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:58
 */
@Data
public class JDKProxy implements InvocationHandler {

    //远端rpc功能的接口
    //具体在这个案例中 UserService OrderService;
    private Class targetInterface;

    private Cluster cluster;
    private LoadBalancer loadBalancer;
    private Transport transport;
    private Registry registry;

    private List<HostAndPort> hostAndPorts;

    public JDKProxy(Class targetInterface) {
        this.targetInterface = targetInterface;
    }

    public Object createProxy() {
        //1 从注册中心中发现服务列表
        hostAndPorts = registry.getAvailableService(targetInterface.getName());
        //2 服务列表的订阅
        registry.subscribeService(targetInterface.getName(), hostAndPorts);
        return Proxy.newProxyInstance(targetInterface.getClassLoader(), new Class[]{targetInterface}, this);
    }

    //通过集群进行远端rpc的调用
    /*
         Cluster
         LoadBalancer
         Transport
         Regiestry
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInvokeData methodInvokeData = new MethodInvokeData(targetInterface, method.getName(), method.getParameterTypes(), args);
        Result result = cluster.invoke(hostAndPorts, loadBalancer, transport, methodInvokeData);

        if (result.getException() != null) {
            throw result.getException();
        }

        return result.getResultValue();
    }
}

