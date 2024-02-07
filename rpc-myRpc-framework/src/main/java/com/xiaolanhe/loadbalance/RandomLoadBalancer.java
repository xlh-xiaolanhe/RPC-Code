package com.xiaolanhe.loadbalance;

import com.xiaolanhe.common.HostAndPort;

import java.util.List;
import java.util.Random;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:54
 */
public class RandomLoadBalancer implements LoadBalancer{

    private Random random = new Random();

    @Override
    public HostAndPort select(List<HostAndPort> hostAndPortList) {
        if(null == hostAndPortList || hostAndPortList.size() == 0)
        {
            throw new RuntimeException("hostAndPortList is empty");
        }
        int index = random.nextInt(hostAndPortList.size());
        return hostAndPortList.get(index);
    }
}
