package com.xiaolanhe.serverCall;

import io.grpc.ForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 20:23
 */
@Slf4j
public class CustomServerCall<ReqT, RespT> extends ForwardingServerCall.SimpleForwardingServerCall<ReqT,RespT> {
    public CustomServerCall(ServerCall<ReqT, RespT> delegate) {
        super(delegate);
    }

    @Override
    //指定发送消息的数量 【响应消息】
    public void request(int numMessages) {
        log.debug("response 指定消息的数量 【request】");
        super.request(numMessages);
    }

    @Override
    //设置响应头
    public void sendHeaders(Metadata headers) {
        log.debug("response 设置响应头 【sendHeaders】");
        super.sendHeaders(headers);
    }

    @Override
    //响应数据
    public void sendMessage(RespT message) {
        log.debug("response 响应数据  【send Message 】 {} ", message);
        super.sendMessage(message);
    }

    @Override
    //关闭连接
    public void close(Status status, Metadata trailers) {
        log.debug("response 关闭连接 【close】");
        super.close(status, trailers);
    }
}
