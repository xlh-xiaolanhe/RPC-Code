package com.xiaolanhe.discover;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/4 21:43
 */
public class ServiceDiscoverImpl implements ServiceDiscover{

    private CuratorFramework client;

    private String basePath = "/zk-register";

    public ServiceDiscoverImpl() {
        client = CuratorFrameworkFactory.newClient("192.168.235.132:2181,192.168.235.133:2181,192.168.235.134:2181", 1000, 1000, new ExponentialBackoffRetry(1000, 3, 1000));
        this.client.start();
    }

    /**
     * desciption: 根据服务名获取它的子路径，即获取服务列表信息
      * @param serviceName
     * @return java.util.List<java.lang.String>
    */
    @Override
    public List<String> discoverService(String serviceName) {
        String servicePath = basePath + "/" + serviceName;

        try {
            if(client.checkExists().forPath(servicePath) == null){
                throw new RuntimeException("service not exist");
            }
            return client.getChildren().forPath(servicePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("discoverService happens exception");
        }
    }

    @Override
    public void registerWatch(String serviceName) {
        String servicePath = basePath + "/"+serviceName;
        CuratorCache curatorCache = CuratorCache.build(client, servicePath);

        CuratorCacheListener curatorCacheListener = CuratorCacheListener.builder().forPathChildrenCache(servicePath, client, new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                List<String> paths = client.getChildren().forPath(servicePath);
                System.out.println("service list has been changed....");
                for (String name : paths) {
                    System.out.println(name);
                }
            }
        }).build();

        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.start();
    }
}
