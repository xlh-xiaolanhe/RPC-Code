package com.xiaolanhe.service;

import com.xiaolanhe.TestProto;
import com.xiaolanhe.TestServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 19:23
 */
public class TestServiceImpl extends TestServiceGrpc.TestServiceImplBase {
    @Override
    public void testSuns(TestProto.TestRequest request, StreamObserver<TestProto.TestResponse> responseObserver) {
        String name = request.getName();
        System.out.println("name = " + name);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        responseObserver.onNext(TestProto.TestResponse.newBuilder().setResult("test is ok").build());
        responseObserver.onCompleted();
    }
}
