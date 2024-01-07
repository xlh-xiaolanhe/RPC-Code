package com.xiaolanhe.streamTracer;

import io.grpc.ServerStreamTracer;
import lombok.extern.slf4j.Slf4j;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/7 20:55
 */

@Slf4j
public class CustomServerStreamTracer extends ServerStreamTracer {
    //inbound 拦截请求
    @Override
    public void inboundMessage(int seqNo) {
        super.inboundMessage(seqNo);
    }

    @Override
    public void inboundWireSize(long bytes) {
        super.inboundWireSize(bytes);
    }

    @Override
    public void inboundMessageRead(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
        log.debug("server: 获得client发送的请求消息 ...{} {} {}", seqNo, optionalWireSize, optionalUncompressedSize);
        super.inboundMessageRead(seqNo, optionalWireSize, optionalUncompressedSize);
    }

    @Override
    public void inboundUncompressedSize(long bytes) {
        super.inboundUncompressedSize(bytes);
    }

    //outbound 拦截请求

    @Override
    public void outboundMessage(int seqNo) {
        super.outboundMessage(seqNo);
    }


    @Override
    public void outboundMessageSent(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
        log.debug("server: 响应数据的拦截 ...{} {} {}", seqNo, optionalWireSize, optionalUncompressedSize);
        super.outboundMessageSent(seqNo, optionalWireSize, optionalUncompressedSize);
    }

    @Override
    public void outboundWireSize(long bytes) {
        super.outboundWireSize(bytes);
    }

    @Override
    public void outboundUncompressedSize(long bytes) {
        super.outboundUncompressedSize(bytes);
    }
}

