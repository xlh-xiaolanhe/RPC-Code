package com.xiaolanhe.cluster;

import com.xiaolanhe.common.HostAndPort;
import com.xiaolanhe.loadbalance.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import com.xiaolanhe.protocol.MethodInvokeData;
import com.xiaolanhe.protocol.Result;
import com.xiaolanhe.transport.Transport;

import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:53
 */
@Slf4j
public class FailFastCluster implements Cluster{
    @Override
    public Result invoke(List<HostAndPort> hostAndPorts, LoadBalancer loadBalancer, Transport transport, MethodInvokeData methodInvokeData) {
        HostAndPort hostAndPort = loadBalancer.select(hostAndPorts);
        Result result = null;

        try{
            result = transport.invoke(hostAndPort, methodInvokeData);
            transport.close();
        }catch (Exception e){
            transport.close();
            log.error("集群调用产生错误..使用FailFast的方式进行容错....");
            throw new RuntimeException(e);
        }
        return result;
    }
}
