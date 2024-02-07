package com.xiaolanhe.proxy;

import com.xiaolanhe.cluster.Cluster;
import com.xiaolanhe.common.HostAndPort;
import com.xiaolanhe.loadbalance.LoadBalancer;
import com.xiaolanhe.protocol.Result;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import com.xiaolanhe.protocol.MethodInvokeData;
import com.xiaolanhe.registry.Registry;
import com.xiaolanhe.transport.Transport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 11:00
 */

/**
 * FactoryBean接口是一个用于产生其他bean实例的工厂接口，而不是直接作为一个简单的bean暴露给Spring容器。
 * 这意味着，当Spring容器遇到一个FactoryBean的实现时，它会使用该工厂bean生成的对象，而不是工厂bean本身，作为bean的实例。
 */
@Data
public class ProxyFactoryBean implements FactoryBean, InvocationHandler {

    private Class targetInterface;

    private Cluster cluster;

    private Transport transport;

    private Registry registry;

    private LoadBalancer loadBalancer;

    private List<HostAndPort> hostAndPortList;

    public ProxyFactoryBean(Class targetInterface){
        this.targetInterface = targetInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInvokeData methodInvokeData = new MethodInvokeData(targetInterface, method.getName(), method.getParameterTypes(), args);
        Result result = cluster.invoke(hostAndPortList, loadBalancer, transport, methodInvokeData);

        if(null != result.getException())
        {
            throw result.getException();
        }

        return result.getResultValue();
    }

    @Override
    /** 这个方法负责返回实际的bean实例。创建代理  */
    public Object getObject() throws Exception {
        hostAndPortList = registry.getAvailableService(targetInterface.getName());

        registry.subscribeService(targetInterface.getName(), hostAndPortList);

        return Proxy.newProxyInstance(targetInterface.getClassLoader(), new Class[]{targetInterface}, this);
    }

    @Override
    public Class<?> getObjectType() {
        return targetInterface;
    }
}
