package com.xiaolanhe.stream_interceptor;

import com.xiaolanhe.streamTracer.CustomClientStreamTracerFactory;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 20:45
 */
@Slf4j
public class CustomerClientInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        log.debug("执行客户端拦截器...");

        //把自己开发的ClientStreamTracerFactory融入到gRPC体系
        callOptions = callOptions.withStreamTracerFactory(new CustomClientStreamTracerFactory<>());
        return channel.newCall(methodDescriptor, callOptions);
    }
}
