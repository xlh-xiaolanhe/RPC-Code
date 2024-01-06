package com.xiaolanhe;

import com.xiaolanhe.service.HelloServiceImpl;
import com.xiaolanhe.service.TestServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 13:19
 */
public class GprcServer1 {
    public static void main(String[] args) throws InterruptedException, IOException {
        // 绑定端口
        ServerBuilder serverBuilder = ServerBuilder.forPort(9000);

        // 发布服务
        serverBuilder.addService(new HelloServiceImpl());
        serverBuilder.addService(new TestServiceImpl());

        // 创建服务对象
        Server server = serverBuilder.build();

        server.start();
        server.awaitTermination();
    }
}
