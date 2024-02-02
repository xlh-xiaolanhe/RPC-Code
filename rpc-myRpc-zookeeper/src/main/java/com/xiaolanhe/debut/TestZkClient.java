package com.xiaolanhe.debut;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/1 23:41
 */
public class TestZkClient {
    private static ZooKeeper zookeeper;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        // zk集群中的每一个节点的ip:port

        String connectionInfo = "192.168.235.132:2181,192.168.235.133:2181,192.168.235.134:2181";
        int sessionTimeout = 2000;

        zookeeper = new ZooKeeper(connectionInfo, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    // 获取 /sun 节点的所有子节点的列表，并重新设置对该节点的监视
                    List<String> children = zookeeper.getChildren("/sun", true);
                    for(String child : children){
                        System.out.println(child);
                    }
                } catch (KeeperException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        /* 1. 创建节点的操作 */
        // ZooDefs.Ids.OPEN_ACL_UNSAFE : 表示该节点对所有客户端都是完全开放的，没有任何安全限制。在实际的生产环境中，使用这个设置可能会带来安全风险
        //zookeeper.create("/suns", "xiaolanhe".getBytes(Charset.defaultCharset()), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        /* 2. 修改节点数据*/
       // zookeeper.setData("/suns", "xiaohei".getBytes(Charset.defaultCharset()), -1);

        /* 3. 查询 */
        /*List<String> children = zookeeper.getChildren("/", false);
        for(String child : children){
            System.out.println(child);
        }*/

        // zookeeper.delete("/suns", -1);

        /*. 未来判断一个节点是否存在 */
       zookeeper.exists("/xiaohei", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("这个节点是存在的");
            }
        });

       System.in.read();
    }
}
