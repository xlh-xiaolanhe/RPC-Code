package com.xiaolanhe.service;

import com.google.protobuf.ProtocolStringList;
import com.xiaolanhe.HelloProto;
import com.xiaolanhe.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/6 12:14
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    /**
      1. 接受client提交的参数  request.getParameter()
      2. 业务处理 service+dao 调用对应的业务功能。
      3. 提供返回值
     */
    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        // 1.接受client的请求参数
        String name = request.getName();

        // 2.业务处理

        System.out.println("name parameter: " + name );

        // 3.封装响应
        //3.1 创建相应对象的构造者
        HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
        //3.2 填充数据
        builder.setResult("hello method invoke success");

        //3.3封装响应
        HelloProto.HelloResponse helloResponse = builder.build();

        //返回响应
        responseObserver.onNext(helloResponse);

        // 通知客户端本次响应结束
        responseObserver.onCompleted();

    }


    /**
     * desciption 处理包含多个值的请求
      * @param request
     * @param responseObserver
     * @return void
     * @Date 2024/1/6 13:52
    */
    @Override
    public void hello2(HelloProto.HelloRequestMultiValue request, StreamObserver<HelloProto.HelloResponseMultiValue> responseObserver) {
        ProtocolStringList nameList = request.getNameList();
        for(String s : nameList)
        {
            System.out.println("s = " + s);
        }

        System.out.println("HelloServiceImpl.hello1");

        HelloProto.HelloResponseMultiValue.Builder builder = HelloProto.HelloResponseMultiValue.newBuilder();
        builder.setResult("ok");
        HelloProto.HelloResponseMultiValue helloResponse1 = builder.build();

        responseObserver.onNext(helloResponse1);
        responseObserver.onCompleted();
    }

    /**
     * desciption  服务端流式 rpc
      * @param request
     * @param responseObserver
     * @return void
     * @Date 2024/1/6 14:07
    */
    @Override
    public void client2ServerStream(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        //1 接受client的请求参数
        String name = request.getName();
        //2 做业务处理
        System.out.println("name = " + name);

        //3 根据业务处理的结果，提供响应
        for (int i = 0; i < 9; i++) {
            HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
            builder.setResult("处理的结果 = " + i);
            HelloProto.HelloResponse helloResponse = builder.build();

            responseObserver.onNext(helloResponse);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        responseObserver.onCompleted();
    }

    /**
     * desciption  客户端流式 rpc
      * @param responseObserver
     * @return io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloRequest>
     * @Date 2024/1/6 14:14
    */

    // 返回的是 StreamObserver<HelloProto.HelloRequest> 流的监控者，监听何时请求发送到了服务端
    @Override
    public StreamObserver<HelloProto.HelloRequest> clientStream2Server(StreamObserver<HelloProto.HelloResponse> responseObserver) {
        return new StreamObserver<HelloProto.HelloRequest>() {
            @Override
            public void onNext(HelloProto.HelloRequest helloRequest) {
                System.out.println("接受到了client发送一条消息 " + helloRequest.getName());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("client的所有消息 都发送到了 服务端 ....");

                //提供响应：响应的目的：当接受了全部client提交的信息，并处理后，提供相应
                HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
                builder.setResult("all is ok");
                HelloProto.HelloResponse helloResponse = builder.build();

                responseObserver.onNext(helloResponse);
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * desciption  双向流 RPC 服务接口的开发
     *
      * @param responseObserver
     * @return io.grpc.stub.StreamObserver<com.xiaolanhe.HelloProto.HelloRequest>
     * @Date 2024/1/6 14:48
    */
    @Override
    public StreamObserver<HelloProto.HelloRequest> clientStream2ServerStream(StreamObserver<HelloProto.HelloResponse> responseObserver) {
        return new StreamObserver<HelloProto.HelloRequest>() {
            @Override
            public void onNext(HelloProto.HelloRequest helloRequest) {
                System.out.println("接受到client 提交的消息 "+ helloRequest.getName());
                responseObserver.onNext(HelloProto.HelloResponse.newBuilder().setResult("response "+ helloRequest.getName()+" result ").build());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("接受到了所有的请求消息 ... ");
                responseObserver.onCompleted();
            }
        };
    }
}
