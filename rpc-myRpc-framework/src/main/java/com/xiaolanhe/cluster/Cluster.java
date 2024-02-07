package com.xiaolanhe.cluster;

import com.xiaolanhe.common.HostAndPort;
import com.xiaolanhe.loadbalance.LoadBalancer;
import com.xiaolanhe.protocol.MethodInvokeData;
import com.xiaolanhe.protocol.Result;
import com.xiaolanhe.transport.Transport;

import java.util.List;

/**
 * @author xiaolanhe
 * @version V1.0
 * @Package cluster
 * @date 2024/2/7 10:52
 */
public interface Cluster {

    /**
     * 集群调用方式
     * @param hostAndPorts
     * @param loadBalancer
     * @param transport
     * @param methodInvokeData
     * @return
     */
    Result invoke(List<HostAndPort> hostAndPorts, LoadBalancer loadBalancer, Transport transport, MethodInvokeData methodInvokeData);
}
