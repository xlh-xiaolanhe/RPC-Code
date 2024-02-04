package com.xiaolanhe.discover;/**
 * @Package com.xiaolanhe.discover
 * @author xiaolanhe
 * @date 2024/2/4 21:41
 * @version V1.0
 */

import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/4 21:41
 */
public interface ServiceDiscover {

    // 与具体的服务节点 解耦合
    List<String> discoverService(String serviceName);

    /** 监控服务列表的变化 不用每一次先去获取内容 */
    void registerWatch(String serviceName);
}
