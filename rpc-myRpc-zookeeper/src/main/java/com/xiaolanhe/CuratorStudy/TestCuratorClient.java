package com.xiaolanhe.CuratorStudy;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/2 22:50
 */
public class TestCuratorClient {
    public static void main(String[] args) throws Exception {
        // 设置client重试
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(1000, 3, 3);

        String connectString = "192.168.235.132:2181,192.168.235.133:2181,192.168.235.134:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, 3000, 3000, backoffRetry);
        // 开启客户端
        client.start();

        /* use 4 查询 */
        /** 只能拿到儿子，无法拿到孙子 */
        List<String> children = client.getChildren().forPath("/z1");
        for(String child : children)
        {
            System.out.println("node = " + child);
        }
         /** 节点上面的数据 */
        byte[] bytes = client.getData().forPath("/z1/z2/z3");
        System.out.println("z3 node value = " + new String(bytes));



        /* use 3 修改数据 */
        //client.setData().forPath("/xiaojr", "不良帅".getBytes(StandardCharsets.UTF_8));


        /* use 2 删除*/
        //client.delete().forPath("/hello0000000007");
        /** 默认使用delete方法进行删除时，他只能删除单级目录 */
        //client.delete().forPath("/z1");

        /** 删除多级目录的操作 */
        //client.delete().deletingChildrenIfNeeded().forPath("/z1");



        /* use 1 创建*/
        /** 创建目录 在使用curator过程中，如果创建目录没有指定对应的数据，那么curator会把client的ip地址作为这个目录数据的默认值。*/
        //client.create().forPath("/xiaohei");

        /** 创建目录 同时指定目录的数据 */
        // client.create().forPath("/xiaojr", "xiaolanhe".getBytes(StandardCharsets.UTF_8));

        /** 创建多级目录 */
        //client.create().creatingParentsIfNeeded().forPath("/z1/z2/z3", "xiaolanhe".getBytes(StandardCharsets.UTF_8));

        /** 创建不同类型节点 4种类型的节点 */
        //client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/hello");
    }
}
