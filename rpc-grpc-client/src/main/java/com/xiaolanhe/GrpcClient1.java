package com.xiaolanhe;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 13:25
 */
public class GrpcClient1 {
    public static void main(String[] args) {
        // 1. 创建通信的管道
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000)
                .usePlaintext()
                .build();

        try{
            // 2. 获取代理对象
            HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(managedChannel);

            // 3. 完成RPC调用
            // 3.1 准备参数
            HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
            builder.setName("xiaolanhe");
            HelloProto.HelloRequest helloRequest = builder.build();

            // 3.2 进行功能rpc调用，获取响应内容
            HelloProto.HelloResponse helloResponse = helloServiceBlockingStub.hello(helloRequest);
            String result = helloResponse.getResult();
            System.out.println("get return result: " + result);

        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }finally {
            managedChannel.shutdown();
        }

    }
}
