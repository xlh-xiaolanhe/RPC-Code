package com.xiaolanhe.CuratorStudy;/**
 * @Package com.xiaolanhe.debut.CuratorStudy
 * @author xiaolanhe
 * @date 2024/2/2 23:39
 * @version V1.0
 */

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/2 23:39
 */

/**
  监听子目录的变化 （不包括父目录）
*/
public class TestPathChildrenCacheListener {
    public static void main(String[] args) throws IOException {
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(1000, 3, 3);

        String connectString = "192.168.235.132:2181,192.168.235.133:2181,192.168.235.134:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, 3000, 3000, backoffRetry);
        client.start();

        CuratorCache curatorCache = CuratorCache.build(client, "/z1/z2");
        CuratorCacheListener curatorCacheListener = CuratorCacheListener.builder().forPathChildrenCache("z1/z2", client, new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("child path change");
            }
        }).build();

        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.start();

        System.in.read();
    }
}
