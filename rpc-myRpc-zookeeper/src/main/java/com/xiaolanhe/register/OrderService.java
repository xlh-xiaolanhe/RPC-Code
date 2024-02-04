package com.xiaolanhe.register;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/4 21:34
 */

import java.io.IOException;

/** 目的: 模拟后续RPC服务的集群，最终通过ServiceRegistry 进行注册 */
public class OrderService  {
    public static void main(String[] args) throws IOException {
        // 1. 服务的类型 2. 服务ip地址 3 服务的端口号 port
        String serviceName = OrderService.class.getSimpleName();

        // Environment parameter
        String ip = System.getenv("ip");
        String port = System.getenv("port");

        System.out.println("serviceName " + serviceName);
        System.out.println("ip = " + ip);
        System.out.println("port = " + port);

        // 应用Netty  ServerBootStrap 服务 -->  序列化

        // 服务注册
        new ZookeeperServiceRegistry(serviceName, ip, port).register();

        System.in.read();
    }
}
