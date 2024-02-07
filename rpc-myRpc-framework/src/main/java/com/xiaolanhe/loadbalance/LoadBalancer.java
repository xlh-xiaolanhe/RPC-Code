package com.xiaolanhe.loadbalance;

import com.xiaolanhe.common.HostAndPort;

import java.util.List;

/**
 * @author xiaolanhe
 * @version V1.0
 * @Package loadbalance
 * @date 2024/2/7 10:54
 */
public interface LoadBalancer {
    HostAndPort select(List<HostAndPort> hostAndPortList);
}
