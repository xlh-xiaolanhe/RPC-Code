package com.xiaolanhe.consul;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/29 22:10
 */
public class TestServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1 模拟服务
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        int port = new Random().nextInt(65535);
        serverSocketChannel.bind(new InetSocketAddress(port));

        //2 consul java client进行注册服务
        // 连接到consul服务器
        Consul consulConnection = Consul.builder().build();
        // 获得consul客户端对象
        AgentClient agentClient = consulConnection.agentClient();

        String serverId = "Server-" + UUID.randomUUID();

        // 进行服务注册
        Registration service = ImmutableRegistration.builder()
                .name("grpc-server")
                .id(serverId)
                .address("localhost")
                .port(port)
                .tags(Collections.singletonList("server"))
                .meta(Collections.singletonMap("version", "1.0"))
                //通过tcp的方式进行健康检查
                .check(Registration.RegCheck.tcp("localhost", port, 10))
                .build();

        agentClient.register(service);
        Thread.sleep(100 * 1000);
    }
}
