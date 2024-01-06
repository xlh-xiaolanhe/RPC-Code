package com.xiaolanhe;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 14:19
 */
public class GrpcClient4 {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();

        try{
            HelloServiceGrpc.HelloServiceStub helloServiceStub = HelloServiceGrpc.newStub(managedChannel);

            HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
            builder.setName("xiaolanhe");
            HelloProto.HelloRequest helloRequest = builder.build();

            // 采用监听方式处理服务端的响应
            helloServiceStub.client2ServerStream(helloRequest, new StreamObserver<HelloProto.HelloResponse>(){
                @Override
                public void onNext(HelloProto.HelloResponse helloResponse) {
                    // 服务端 响应了 一个消息后，需要立即处理的话。把代码写在这个方法中。
                    System.out.println("服务端每一次响应的信息 " + helloResponse.getResult());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    //需要把服务端 响应的所有数据 拿到后，在进行业务处理。
                    System.out.println("服务端响应结束 后续可以根据需要 在这里统一处理服务端响应的所有内容");
                }
            });
            managedChannel.awaitTermination(12, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            managedChannel.shutdown();
        }
    }
}
