package com.xiaolanhe;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 14:56
 */

/**  双向流 RPC 客户端  */
public class GrpcClient6 {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        try {
            HelloServiceGrpc.HelloServiceStub helloService = HelloServiceGrpc.newStub(managedChannel);

            StreamObserver<HelloProto.HelloRequest> helloRequestStreamObserver = helloService.clientStream2ServerStream(new StreamObserver<HelloProto.HelloResponse>() {
                @Override
                public void onNext(HelloProto.HelloResponse helloResponse) {
                    System.out.println("响应的结果 "+ helloResponse.getResult());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    System.out.println("响应全部结束...");
                }
            });

            // 发送请求
            for (int i = 0; i < 10; i++) {
                helloRequestStreamObserver.onNext(HelloProto.HelloRequest.newBuilder().setName("xiaolanhe " + i).build());
            }
            helloRequestStreamObserver.onCompleted();

            managedChannel.awaitTermination(12, TimeUnit.SECONDS);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            managedChannel.shutdown();
        }
    }
}
