package com.xiaolanhe.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/4 21:08
 */
public class ZookeeperServiceRegistry implements ServiceRegistry{

    private String basePath = "/zk-register";

    private CuratorFramework client;

    /* 通过ip 和 port 识别一个服务提供者 */

    private String ip;

    private String port;

    /**具体的服务类别*/
    private String servicePath;


    public ZookeeperServiceRegistry(String servicePath, String ip, String port) {
        this.servicePath = servicePath;
        this.ip = ip;
        this.port = port;
        client = CuratorFrameworkFactory.newClient("192.168.235.132:2181,192.168.235.133:2181,192.168.235.134:2181", 1000, 1000, new ExponentialBackoffRetry(1000, 3, 1000));
        this.client.start();
    }

    @Override
    public void register() {
        String servicePathName = basePath + "/" + servicePath;
        try {
            if(client.checkExists().forPath(servicePathName) == null){
                this.client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePathName);
            }

            // 将本次待注册的服务提供者注册到服务类别下，节点类别为临时节点
            String urlNode = this.client.create().withMode(CreateMode.EPHEMERAL).forPath(servicePathName + "/" + ip + ":" + port);
            System.out.println("register service success, service url is : " + urlNode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("register service fail");
        }
    }
}
