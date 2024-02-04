package com.xiaolanhe.CuratorStudy;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheBuilder;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.CuratorCacheListenerBuilder;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/2 23:24
 */

/**
   下面这段代码，并没有获得这个节点新的数据，只能监听变化
*/
public class TestNodeCacheListener1 {
    public static void main(String[] args) throws IOException {
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(1000, 3, 3);

        String connectString = "192.168.235.132:2181,192.168.235.133:2181,192.168.235.134:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, 3000, 3000, backoffRetry);
        client.start();

        CuratorCache curatorCache = CuratorCache.build(client, "/xiaojr");
        CuratorCacheListener curatorCacheListener = CuratorCacheListener.builder().forNodeCache(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("node value has been changed");
            }
        }).build();

        // listener 注册到 CuratorCache
        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.start();

        System.in.read();
    }
}
