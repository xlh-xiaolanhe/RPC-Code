package com.xiaolanhe.service;

import com.xiaolanhe.HelloProto;
import com.xiaolanhe.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 12:14
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    /**
      1. 接受client提交的参数  request.getParameter()
      2. 业务处理 service+dao 调用对应的业务功能。
      3. 提供返回值
     */
    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        // 1.接受client的请求参数
        String name = request.getName();

        // 2.业务处理

        System.out.println("name parameter: " + name );

        // 3.封装响应
        //3.1 创建相应对象的构造者
        HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
        //3.2 填充数据
        builder.setResult("hello method invoke success");

        //3.3封装响应
        HelloProto.HelloResponse helloResponse = builder.build();

        //返回响应
        responseObserver.onNext(helloResponse);

        // 通知客户端本次响应结束
        responseObserver.onCompleted();

    }
}
