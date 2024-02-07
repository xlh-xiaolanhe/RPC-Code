package com.xiaolanhe.registry;

import com.xiaolanhe.common.HostAndPort;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;
import java.util.stream.Collectors;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:49
 */
@Slf4j
public class ZookeeperRegistry implements Registry{

    private final int TIME_OUT = 1000 * 10* 30000;

    private CuratorFramework client;

    public ZookeeperRegistry(String zkServerAddress) {
        this.client = CuratorFrameworkFactory.newClient(zkServerAddress, TIME_OUT, TIME_OUT, new ExponentialBackoffRetry(1000, 3, 1000));
        this.client.start();
    }

    @Override
    public void registerService(String targetInterfaceName, HostAndPort hostAndPort) {
        String servicePath = SERVICE_PREFIX + "/" + targetInterfaceName + SERVICE_SUFFIX;

        try{
            if(client.checkExists().forPath(servicePath) == null){
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath);
            }

            String nodeUrl = client.create().withMode(CreateMode.EPHEMERAL).forPath(servicePath + "/" + hostAndPort.getHostName() + ":" + hostAndPort.getPort());
            log.debug("create node: {}", nodeUrl);
        }catch (Exception e){
            log.error("Failed to register service", e);
            throw new RuntimeException("注册 " + targetInterfaceName + " 节点出错...");
        }
    }

    @Override
    public List<HostAndPort> getAvailableService(String targetInterfaceName) {
        String servicePath = SERVICE_PREFIX + "/" + targetInterfaceName + SERVICE_SUFFIX;
        try{
            if(this.client.checkExists().forPath(servicePath) == null)
            {
                return transferServiceList2HostAndName(client.getChildren().forPath(servicePath));
            }else{
                throw new RuntimeException("this is no available service provider");
            }
        }catch (Exception e){
            log.error("service discover happens error...", e);
            throw new RuntimeException("服务的发现产生了异常.....");
        }
    }

    private List<HostAndPort> transferServiceList2HostAndName(List<String> paths) {
        return paths.stream()
                .map(s -> s.split(":"))
                .map(ele -> new HostAndPort(ele[0], Integer.parseInt(ele[1])))
                .collect(Collectors.toList());
    }

    @Override
    public void subscribeService(String targetInterfaceName, List<HostAndPort> existingServices) {
        String servicePath = SERVICE_PREFIX + "/" + targetInterfaceName + SERVICE_SUFFIX;
        CuratorCache curatorCache = CuratorCache.build(client, servicePath);

        CuratorCacheListener curatorCacheListener = CuratorCacheListener.builder().forPathChildrenCache(servicePath, client, new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                // 清理之前的服务列表
                existingServices.clear();

                // 获取最新的服务列表
                existingServices.addAll(getAvailableService(servicePath));
            }
        }).build();

        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.start();
    }
}

