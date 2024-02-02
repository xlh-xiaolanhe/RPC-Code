package com.xiaolanhe.debut.CuratorStudy;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/2 23:43
 */

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;

/**
  监听子目录的变化 （包括父目录）TreeCacheListener

   监听子路径 同时也可以监听 父路径,但是不能处理爷爷
 */
public class TestTreeCacheListener {
    public static void main(String[] args) throws IOException {
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(1000, 3, 3);

        String connectString = "192.168.235.132:2181,192.168.235.133:2181,192.168.235.134:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, 3000, 3000, backoffRetry);
        client.start();

        CuratorCache curatorCache = CuratorCache.build(client, "/z1/z2");
        CuratorCacheListener curatorCacheListener = CuratorCacheListener.builder().forTreeCache(client, new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("---tree cache listener----");
            }
        }).build();

        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.start();

        System.in.read();
    }
}
