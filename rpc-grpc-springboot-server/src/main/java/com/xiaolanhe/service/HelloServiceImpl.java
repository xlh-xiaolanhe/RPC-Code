package com.xiaolanhe.service;

import com.xiaolanhe.HelloProto;
import com.xiaolanhe.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 11:42
 */

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        String name = request.getName();
        System.out.println("name is : " + name );

        responseObserver.onNext(HelloProto.HelloResponse.newBuilder().setResult("this is result ").build());
        responseObserver.onCompleted();
    }
}
