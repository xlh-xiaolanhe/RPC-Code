package com.xiaolanhe.debut.CuratorStudy;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/2 23:30
 */

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.CuratorCacheListenerBuilder;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;

/**
   下面这段代码，既可以监听节点数据变化，同时也可以获取节点新旧值
*/
public class TestNodeCacheListener2 {
    public static void main(String[] args) throws IOException {
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(1000, 3, 3);

        String connectString = "192.168.235.132:2181,192.168.235.133:2181,192.168.235.134:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, 3000, 3000, backoffRetry);
        client.start();

        CuratorCache curatorCache = CuratorCache.build(client, "/xiaojr");
        CuratorCacheListener curatorCacheListener = CuratorCacheListener.builder().forChanges(new CuratorCacheListenerBuilder.ChangeListener() {
            @Override
            public void event(ChildData oldValue, ChildData newValue) {
                byte[] oldValueData = oldValue.getData();
                byte[] newValueData = newValue.getData();

                System.out.println("old value is : " + new String(oldValueData));
                System.out.println("new value is : " + new String(newValueData));
            }
        }).build();

        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.start();

        System.in.read();
    }
}
