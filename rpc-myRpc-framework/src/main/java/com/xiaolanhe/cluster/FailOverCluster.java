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
public class FailOverCluster implements Cluster{

    @Override
    public Result invoke(List<HostAndPort> hostAndPorts, LoadBalancer loadBalancer, Transport transport, MethodInvokeData methodInvokeData) {
        HostAndPort hostAndPort = loadBalancer.select(hostAndPorts);

        log.debug("the host and port to access is: " + hostAndPort.getHostName() + " : " + hostAndPort.getPort());

        Result result;

        try{
            result = transport.invoke(hostAndPort, methodInvokeData);
            transport.close();
        }catch (Exception e){
            log.error("集群调用发生错误，使用FailOver容错 ", e);
            transport.close();

            // 将这个出错的服务移除
            hostAndPorts.remove(hostAndPort);
            if(hostAndPorts.size() == 0){
                throw new RuntimeException("集群出现错误...");
            }else{
                return invoke(hostAndPorts, loadBalancer, transport, methodInvokeData);
            }
        }
        return result;
    }
}
