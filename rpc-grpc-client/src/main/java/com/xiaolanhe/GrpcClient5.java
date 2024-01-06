package com.xiaolanhe;/**
 * @Package com.xiaolanhe
 * @author xiaolanhe
 * @date 2024/1/6 14:47
 * @version V1.0
 */

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 14:47
 */
public class GrpcClient5 {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        try {
            HelloServiceGrpc.HelloServiceStub helloService = HelloServiceGrpc.newStub(managedChannel);

            StreamObserver<HelloProto.HelloRequest> helloRequestStreamObserver = helloService.clientStream2Server(new StreamObserver<HelloProto.HelloResponse>() {
                @Override
                public void onNext(HelloProto.HelloResponse value) {
                    // 监控响应
                    System.out.println("服务端 响应 数据内容为 " + value.getResult());

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    System.out.println("服务端响应结束 ... ");
                }
            });

            //客户端 发送数据 到服务端  多条数据 ，不定时...
            for (int i = 0; i < 10; i++) {
                HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
                builder.setName("xiaolanhe " + i);
                HelloProto.HelloRequest helloRequest = builder.build();

                helloRequestStreamObserver.onNext(helloRequest);
                Thread.sleep(1000);
            }

            helloRequestStreamObserver.onCompleted();

            managedChannel.awaitTermination(12, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            managedChannel.shutdown();
        }
    }
}
