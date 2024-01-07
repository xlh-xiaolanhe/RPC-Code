package com.xiaolanhe.clientCall;

import com.xiaolanhe.listener.CustomCallListener;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.Metadata;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 19:20
 */

/**
   这个类型 适用于控制 拦截 请求发送各个环节
 */
@Slf4j
public class CustomForwardingClientClass<ReqT, RespT> extends ClientInterceptors.CheckedForwardingClientCall<ReqT, RespT> {

    // 都通过super调用，而父类里面又是通过 delegate 来调用真正的方法 。很典型的装饰器模式，将原始的 ClientCall 传进去,可以提供原始的clientCall进行使用
    public CustomForwardingClientClass(ClientCall<ReqT, RespT> delegate) {
        super(delegate);
    }


    // 开始调用
    // 目的： 检查一下这个RPC请求是否可以被发起
    @Override
    protected void checkedStart(Listener<RespT> responselistener, Metadata metadata) throws Exception {
        log.debug("发送请求数据之前的检查.....");
       /** 真正的去发起grpc的请求
         是否真正发送grpc的请求，取决这个start方法的调用
        responselistener： 默认去监听响应，如果想要自己处理响应，可以自己实现这个Listener
        */
        //delegate().start(responselistener, metadata);
        delegate().start(new CustomCallListener<>(responselistener), metadata);
    }

    /**  重写这些方法后，如果不用影响主干调用的话，都必须再用super调用这个方法 */

    //指定发送消息的数量
    @Override
    public void request(int numMessages) {
        //添加一些功能
        log.debug("request 方法被调用 ....");
        super.request(numMessages);
    }

    //发送消息 到缓冲区
    @Override
    public void sendMessage(ReqT message) {
        log.debug("sendMessage 方法被调用... {} ", message);
        super.sendMessage(message);
    }

    //开启半连接 请求消息无法再发送，但是可以接受响应的消息
    @Override
    public void halfClose() {
        log.debug("halfClose 方法被调用... 开启了半连接");
        super.halfClose();
    }
}
