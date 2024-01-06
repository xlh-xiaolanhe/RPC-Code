package com.xiaolanhe;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 13:54
 */

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * desciption  发送多个参数
*/
public class GrpcClient2 {
    public static void main(String[] args) {
        //1 创建通信的管道
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        //2 获得代理对象 stub
        try {
            HelloServiceGrpc.HelloServiceBlockingStub helloService = HelloServiceGrpc.newBlockingStub(managedChannel);
            //3. 完成RPC调用
            //3.1 准备参数
            HelloProto.HelloRequestMultiValue.Builder builder = HelloProto.HelloRequestMultiValue.newBuilder();
            builder.addName("xiaolanhe1");
            builder.addName("xiaolanhe2");
            builder.addName("xiaolanhe3");
            HelloProto.HelloRequestMultiValue helloRequest1 = builder.build();

            HelloProto.HelloResponseMultiValue helloResponse1 = helloService.hello2(helloRequest1);
            String result = helloResponse1.getResult();
            System.out.println("result = " + result);


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            managedChannel.shutdown();
        }
    }
}
