package com.xiaolanhe.register;


import java.io.IOException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/4 21:21
 */
public class TestServiceRegistry {
    public static void main(String[] args) throws IOException {
        ZookeeperServiceRegistry serviceRegistry = new ZookeeperServiceRegistry("orderService", "192.168.0.12", "8080");
        serviceRegistry.register();
        System.in.read();
    }

}
