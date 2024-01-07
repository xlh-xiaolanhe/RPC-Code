package com.xiaolanhe.listener;

import io.grpc.ClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 19:51
 */

/**
    用于客户端监听响应
*/
@Slf4j
public class CustomCallListener<RespT> extends ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT> {
    public CustomCallListener(ClientCall.Listener<RespT> delegate) {
        super(delegate);
    }

    @Override
    public void onHeaders(Metadata headers) {
        log.info("响应头信息 回来了......");
        super.onHeaders(headers);
    }

    @Override
    public void onMessage(RespT message) {
        log.info("响应的数据 回来了.....{} ", message);
        super.onMessage(message);
    }

    //onHeaders这个方法 会在onNext调用后 监听到数据  也就是如果onNext不执行的话，这个方法不会被触发
    //onMessage这个方法 会在onCompleted调用后 监听到数据  与上同理
}
