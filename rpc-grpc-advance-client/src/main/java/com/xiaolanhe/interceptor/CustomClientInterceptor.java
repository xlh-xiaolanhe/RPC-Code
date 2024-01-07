package com.xiaolanhe.interceptor;

import com.xiaolanhe.clientCall.CustomForwardingClientClass;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 19:08
 */
@Slf4j
public class CustomClientInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        log.debug("这是一个拦截启动的处理 ,统一的做了一些操作 ....");

        // return channel.newCall(methodDescriptor, callOptions);

        /**
           如果我们需要用复杂客户端拦截器 ，就需要对原始的ClientCall进行包装
           那么这个时候，就不能返回原始ClientCall对象，
           应该返回 包装的ClientCall ---> CustomForwardingClientClass
         */

        return new CustomForwardingClientClass<>(channel.newCall(methodDescriptor, callOptions));
    }
}
