package com.xiaolanhe.listener;

import io.grpc.ForwardingServerCallListener;
import io.grpc.ServerCall;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 20:32
 */
@Slf4j
public class CustomServerCallListener<ReqT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {

    public CustomServerCallListener(ServerCall.Listener<ReqT> delegate) {
        super(delegate);
    }

    @Override
    //准备接受请求数据
    public void onReady() {
        log.debug("onRead Method Invoke....");
        super.onReady();
    }

    @Override
    public void onMessage(ReqT message) {
        log.debug("接受到了 请求提交的数据  {} ", message);
        super.onMessage(message);
    }

    @Override
    public void onHalfClose() {
        log.debug("监听到了 半连接...");
        super.onHalfClose();
    }

    @Override
    public void onComplete() {
        log.debug("服务端 onCompleted()...");
        super.onComplete();
    }

    @Override
    public void onCancel() {
        log.debug("出现异常后 会调用这个方法... 关闭资源的操作");
        super.onCancel();
    }
}
