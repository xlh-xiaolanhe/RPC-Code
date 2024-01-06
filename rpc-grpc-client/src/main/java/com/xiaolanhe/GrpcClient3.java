package com.xiaolanhe;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
  *  发送一个请求，接收服务端返回的多个结果
 *@author: xiaolanhe
 *@createDate: 2024/1/6 14:08
 */

public class GrpcClient3 {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();

        try{
            // 这里使用的是阻塞式  一旦发起服务器的流式调用后，整个程序阻塞，需要服务端返回了所有的信息后，才会往下执行 （正确使用方式见 Client4 ）
            HelloServiceGrpc.HelloServiceBlockingStub helloService = HelloServiceGrpc.newBlockingStub(managedChannel);

            HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
            builder.setName("xiaolanhe");

            HelloProto.HelloRequest helloRequest = builder.build();

            Iterator<HelloProto.HelloResponse> helloResponseIterator = helloService.client2ServerStream(helloRequest);
            while(helloResponseIterator.hasNext()){
                HelloProto.HelloResponse helloResponse = helloResponseIterator.next();
                System.out.println("get return res : " + helloResponse.getResult());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            managedChannel.shutdown();
        }

    }
}
