package com.xiaolanhe.service;

import com.xiaolanhe.HelloProto;
import com.xiaolanhe.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 19:33
 */
@Slf4j
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        String name = request.getName();
        System.out.println("name = " + name);

        responseObserver.onNext(HelloProto.HelloResponse.newBuilder().setResult("this is result").build());
        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<HelloProto.HelloRequest> hello1(StreamObserver<HelloProto.HelloResponse> responseObserver) {
        return new StreamObserver<HelloProto.HelloRequest>() {
            @Override
            public void onNext(HelloProto.HelloRequest helloRequest) {
                log.debug("request message is {} ", helloRequest.getName());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.debug("request message all recive ....");
                responseObserver.onNext(HelloProto.HelloResponse.newBuilder().setResult("result 1").build());
                responseObserver.onNext(HelloProto.HelloResponse.newBuilder().setResult("result 2").build());
                responseObserver.onCompleted();
            }
        };
    }
}
