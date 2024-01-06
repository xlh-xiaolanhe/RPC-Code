package com.xiaolanhe;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 19:25
 */
public class GrpcClient7 {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();

        try{
            // 创建异步代理对象
            TestServiceGrpc.TestServiceFutureStub testServiceFutureStub = TestServiceGrpc.newFutureStub(managedChannel);
            ListenableFuture<TestProto.TestResponse> responseListenableFuture = testServiceFutureStub.testSuns(TestProto.TestRequest.newBuilder().setName("xiaolanhe").build());

            /** 同步操作
            TestProto.TestResponse testResponse = responseListenableFuture.get();
            System.out.println(testResponse.getResult());*/

           /**  异步响应
            *  responseListenableFuture.addListener(() -> {
                System.out.println("异步响应的 rpc 响应回来了");
            }, Executors.newCachedThreadPool());*/

            Futures.addCallback(responseListenableFuture, new FutureCallback<TestProto.TestResponse>() {
                @Override
                public void onSuccess(TestProto.TestResponse result) {
                    System.out.println("result.getResult() = " + result.getResult());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            }, Executors.newCachedThreadPool());


            System.out.println("后续的操作");

            managedChannel.awaitTermination(12, TimeUnit.SECONDS);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            managedChannel.shutdown();
        }
    }
}
