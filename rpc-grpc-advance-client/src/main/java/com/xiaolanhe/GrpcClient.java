package com.xiaolanhe;


import com.xiaolanhe.interceptor.CustomClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 19:07
 */
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000)
                .intercept(new CustomClientInterceptor())
                .usePlaintext()
                .build();

        try{
            HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(managedChannel);
            HelloProto.HelloResponse helloResponse = helloServiceBlockingStub.hello(HelloProto.HelloRequest.newBuilder().setName("xiaolanhe").build());

            System.out.println("helloResponse.getResult() = " + helloResponse.getResult());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            managedChannel.shutdown();
        }
    }
}
