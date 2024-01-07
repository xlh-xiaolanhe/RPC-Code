package com.xiaolanhe;

import com.xiaolanhe.interceptor.CustomServerInterceptor;
import com.xiaolanhe.service.HelloServiceImpl;
import com.xiaolanhe.streamTracer.CustomServerStreamFactory;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 19:32
 */
public class GrpcServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(9000);
        serverBuilder.addService(new HelloServiceImpl());
        //serverBuilder.intercept(new CustomServerInterceptor());
        serverBuilder.addStreamTracerFactory(new CustomServerStreamFactory());
        Server server = serverBuilder.build();

        server.start();
        server.awaitTermination();
    }
}
