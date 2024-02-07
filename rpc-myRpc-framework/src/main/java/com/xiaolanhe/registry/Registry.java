package com.xiaolanhe.registry;/**
 * @Package registry
 * @author xiaolanhe
 * @date 2024/2/7 10:48
 * @version V1.0
 */

import com.xiaolanhe.common.HostAndPort;

import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:48
 */
public interface Registry {

    String SERVICE_PREFIX = "/rpc";
    String SERVICE_SUFFIX = "/com/xiaolanhe/provider";

    /**
     * 服务的注册
     *
     * @param targetInterfaceName 服务名
     * @param hostAndPort         服务提供者信息
     */
    void registerService(String targetInterfaceName, HostAndPort hostAndPort);

    /**
     * 服务发现  获取服务列表
     * @param targetInterfaceName
     * @return
     */
    List<HostAndPort> getAvailableService(String targetInterfaceName);

    /**
     * 服务订阅
     * @param targetInterfaceName
     * @param existingServices
     */
    void subscribeService(String targetInterfaceName, List<HostAndPort> existingServices);
}
