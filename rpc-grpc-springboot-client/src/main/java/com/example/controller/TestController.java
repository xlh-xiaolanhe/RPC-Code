package com.example.controller;

import com.xiaolanhe.HelloProto;
import com.xiaolanhe.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 12:27
 */

@RestController
public class TestController {

    @GrpcClient("grpc-server") // 读取yml文件中配置grpc-server的信息
    private HelloServiceGrpc.HelloServiceBlockingStub stub;

    @RequestMapping("/test1")
    public String test1(String name){
        System.out.println("name " + name);
        HelloProto.HelloResponse helloResponse = stub.hello(HelloProto.HelloRequest.newBuilder().setName(name).build());
        return helloResponse.getResult();
    }
}
