package com.xiaolanhe.interceptor;/**
 * @Package com.xiaolanhe.interceptor
 * @author xiaolanhe
 * @date 2024/1/7 20:16
 * @version V1.0
 */

import com.xiaolanhe.listener.CustomServerCallListener;
import com.xiaolanhe.serverCall.CustomServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 20:16
 */
@Slf4j
public class CustomServerInterceptor implements ServerInterceptor {

    // 对于服务器来说，永远在监听请求，所以返回的是Listener，具体泛型监听的是请求
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        //在服务器端 拦截请求操作的功能 写在这个方法中
        log.debug("服务器端拦截器生效.....");

        //1. 包装ServerCall 处理服务端响应拦截
        CustomServerCall<ReqT, RespT> reqTRespTCustomServerCall = new CustomServerCall<>(serverCall);

        //2. 包装Listener   处理服务端请求拦截
        CustomServerCallListener<ReqT> reqTCustomServerCallListener = new CustomServerCallListener<>(serverCallHandler.startCall(reqTRespTCustomServerCall, metadata));

        return reqTCustomServerCallListener;

    }
}
